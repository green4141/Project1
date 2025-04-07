package com.tjoeun.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.FavoriteDTO;
import com.tjoeun.dto.PageDTO;
import com.tjoeun.dto.UserDTO;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Value("${path.upload}")
	private String path_upload;
	
	@Value("${page.listcount}")
	private int page_listcount;
	
	@Value("${page.pagenationcount}")
	private int page_pagenationcount;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	private String saveUploadFile(MultipartFile upload_file) {
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		try {
			File file = new File(path_upload + "/");
			if(!file.exists()) file.mkdirs();
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return file_name;
	}
	
	public int addBoardInfo(BoardDTO writeBoardDTO) {
		
		MultipartFile upload_file = writeBoardDTO.getUpload_file();
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			writeBoardDTO.setFile(file_name);
		}
		writeBoardDTO.setUser(loginUserDTO.getIdx());
		
		return boardDAO.addBoardInfo(writeBoardDTO);
	}
	
	public String getBoardInfoName(int board_id) {
		String name = boardDAO.getBoardInfoName(board_id);
		return name;
	}

	public List<BoardDTO> getBoardList(int board_id, int page, Map<String, Object> searchParam) {
		int start = (page - 1) * this.page_listcount;
		RowBounds rowBounds = new RowBounds(start, page_listcount);
		
		List<BoardDTO> boardDTOList = boardDAO.getBoardList(board_id, rowBounds, searchParam);
		
		if (searchParam.containsKey("sort") && searchParam.containsKey("order")) {
			return boardDAO.getSortedBoard(rowBounds, searchParam);
		}
		
		return boardDTOList;
	}
	
	public BoardDTO getBoardInfo(int idx, int user_idx) {
		BoardDTO boardDTO = boardDAO.getBoardInfo(idx);
		
		boardDTO.setExist_favorite(boardDAO.isFavBoardExists(user_idx, idx));
		
		return boardDTO;
	}
	
	public void modifyBoardInfo(BoardDTO modifyBoardDTO) {
		
		MultipartFile upload_file = modifyBoardDTO.getUpload_file();
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			//System.out.println("파일이름 : " + file_name);
			
			modifyBoardDTO.setFile(file_name);
		}
		
		boardDAO.modifyBoardInfo(modifyBoardDTO);
	}
	
	public void deleteBoardInfo(int idx) {
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
}