package com.tjoeun.service;

import java.io.File;
import java.nio.file.Files;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.dao.FileDAO;
import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.FavoriteDTO;
import com.tjoeun.dto.FileDTO;
import com.tjoeun.dto.PageDTO;
import com.tjoeun.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
@RequiredArgsConstructor
public class BoardService {
	
	@Value("${path.upload}")
	private String path_upload;
	
	@Value("${page.listcount}")
	private int page_listcount;
	
	@Value("${page.pagenationcount}")
	private int page_pagenationcount;
	
	private final BoardDAO boardDAO;
	private final FileDAO fileDAO;
	
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	private FileDTO saveUploadFile(MultipartFile upload_file) {
		String file_name = upload_file.getOriginalFilename();
		String file_ext = FilenameUtils.getExtension(file_name);
		String serverFileName = UUID.randomUUID().toString().replace("-", "") + "." + file_ext;
		String uploadPath = path_upload + File.separator + sdf.format(new Date());
		try {
			File uploadPathFile = new File(uploadPath);
			if(!uploadPathFile.exists()) uploadPathFile.mkdirs();
			upload_file.transferTo(new File(uploadPathFile + File.separator + serverFileName));
		} catch(Exception e) {
			e.printStackTrace();
		}
		FileDTO fileDTO = new FileDTO();
		fileDTO.setOriginalname(file_name);
		fileDTO.setServername(uploadPath + File.separator + serverFileName);
		return fileDTO;
	}
	

	// 임시 파일 생성 -> 파일 타입 -> 임시 파일 삭제
	private boolean isValidImageFile(MultipartFile file) {
    try {
        // 임시 파일 생성
        //File tempFile = File.createTempFile("upload_", null);
        Tika tika = new Tika();
        byte[] fileContent = file.getInputStream().readAllBytes();

        // MIME 타입 판별
        String mimeType = tika.detect(fileContent);


        // 허용 MIME 타입 리스트
        return mimeType != null && (
        		mimeType.equals("image/jpg") ||
            mimeType.equals("image/jpeg") ||
            mimeType.equals("image/png") ||
            mimeType.equals("image/gif")
        );
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
	

	public byte[] loadUploadFile(int board_idx) throws IOException {
		FileDTO fileDTO = fileDAO.findByBoardIdx(board_idx);
		File file = new File(fileDTO.getServername());
		FileInputStream fis = new FileInputStream(file);
		byte[] fileBytes = fis.readAllBytes();
		fis.close();
		return fileBytes;
	}

	public int addBoardInfo(BoardDTO writeBoardDTO) {
		writeBoardDTO.setUser(loginUserDTO.getIdx());
		boardDAO.addBoardInfo(writeBoardDTO);
		
		MultipartFile upload_file = writeBoardDTO.getUpload_file();
		if (upload_file != null && upload_file.getSize() > 0) {
	    	if (!isValidImageFile(upload_file)) {
	          	throw new IllegalArgumentException("허용되지 않은 파일 형식입니다. (jpg, jpeg, png, gif만 가능)");
	      	}
			FileDTO file = null;
			file = saveUploadFile(upload_file);
			if(file != null) file.setBoard_idx(writeBoardDTO.getIdx());
			fileDAO.insert(file);
		}
		return writeBoardDTO.getIdx();
	}
	
	public String getBoardInfoName(int board_id) {
		String name = boardDAO.getBoardInfoName(board_id);
		return name;
	}

	public boolean isBoardHasFile(int board_idx) {
		return fileDAO.findByBoardIdx(board_idx) != null;
	}

	public List<BoardDTO> getBoardList(int board_id, int page, Map<String, Object> searchParam) {
		int noticeCount = boardDAO.getTopNotices(board_id).size();
		int generalCount = page_listcount - noticeCount;
		int start = (page - 1) * generalCount;
		RowBounds rowBounds = new RowBounds(start, generalCount);
		
		searchParam.put("board_id", board_id);
		if (searchParam.containsKey("sort") && searchParam.containsKey("order")) {
		return boardDAO.getSortedBoard(rowBounds, searchParam);
		} else return boardDAO.getBoardList(board_id, rowBounds, searchParam);
	}

	
	public BoardDTO getBoardInfo(int idx, int user_idx) {
		BoardDTO boardDTO = boardDAO.getBoardInfo(idx);
		boardDTO.setExist_favorite(boardDAO.isFavBoardExists(user_idx, idx));
		return boardDTO;
	}
	
	public void modifyBoardInfo(BoardDTO modifyBoardDTO) {
		MultipartFile upload_file = modifyBoardDTO.getUpload_file();

		if(upload_file.getSize() > 0) {
      if (!isValidImageFile(upload_file)) {
        throw new IllegalArgumentException("허용되지 않은 파일 형식입니다. (jpg, jpeg, png, gif만 가능)");
      }
		}
		

		boardDAO.modifyBoardInfo(modifyBoardDTO);
		if(upload_file != null && upload_file.getSize() > 0) {
			FileDTO oldFile = fileDAO.findByBoardIdx(modifyBoardDTO.getIdx());
			FileDTO newFile = saveUploadFile(upload_file);
			newFile.setIdx(oldFile.getIdx());
			fileDAO.update(newFile);
			File file = new File(oldFile.getServername());
			file.delete();
		}
	}
	
	public void deleteBoardInfo(int idx) {
		FileDTO fileDTO = fileDAO.findByBoardIdx(idx);
		if(fileDTO != null) {
			File file = new File(fileDTO.getServername());
			file.delete();
			fileDAO.delete(fileDTO.getIdx());
		}
		boardDAO.deleteBoardInfo(idx);
	}
	
	public PageDTO getBoardCount(int board_id, int currentPage, Map<String, Object> searchParamMap) {
		int contentCount = boardDAO.getBoardCount(board_id, searchParamMap);
		PageDTO pageDTO = new PageDTO(contentCount, currentPage, this.page_listcount, this.page_pagenationcount);
		return pageDTO;
	}
	
	public boolean toggleFavBoard(int board_idx) {
    //로그인 되어 있는지 체크
		int user_idx = loginUserDTO.getIdx();

    //좋아요 눌려있는지 확인

	    boolean isFav = boardDAO.isFavBoardExists(user_idx, board_idx);

	    if (isFav) {
	    	//좋아요 눌려있을때 좋아요 버튼을 누를 경우 좋아요 table에서 delete
	      boardDAO.deleteFavBoard(user_idx, board_idx);
	      return false;
	    } else {
	    	//좋아요 안 눌려있을때 좋아요 버튼을 누를 경우 좋아요 table에 insert
	      FavoriteDTO favBoardDTO = new FavoriteDTO();
	      favBoardDTO.setUser_idx(user_idx);
	      favBoardDTO.setBoard_idx(board_idx);
	      boardDAO.addFavBoard(favBoardDTO);
	      return true;
	    }
	}

	public List<BoardDTO> getTopNotices(int board_id) {
	    return boardDAO.getTopNotices(board_id);
	}
	
	public List<BoardDTO> getGeneralBoardListExcludingNotices(int count) {
	    RowBounds rowBounds = new RowBounds(0, count); // 0부터 count개 가져오기
	    return boardDAO.getGeneralBoardListExcludingNotices(rowBounds);
	}

}