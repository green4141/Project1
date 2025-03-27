package com.tjoeun.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.dto.BoardDTO;
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
			//System.out.println("파일이름 : " + file_name);
			//System.out.println("board_id: " + writeBoardDTO.getBoard_id());
			//System.out.println("idx: " + writeBoardDTO.getIdx());
			writeBoardDTO.setFile(file_name);
		}
		writeBoardDTO.setUser(loginUserDTO.getIdx());
		
		return boardDAO.addBoardInfo(writeBoardDTO);
	}
	
	public String getBoardInfoName(int board_id) {
		String name = boardDAO.getBoardInfoName(board_id);
		return name;
	}

	public List<BoardDTO> getBoardList(int board_id, int page){
		
		/*
		  0(page) ->  0(start)   
		  1(page) -> 10(start)
		  2(page) -> 20(start)
		  한 page 당 게시글의 개수 : page_listcount (10)
		*/
		int start = (page - 1) * this.page_listcount;
		RowBounds rowBounds = new RowBounds(start, page_listcount);
		
		List<BoardDTO> boardDTOList = boardDAO.getBoardList(board_id, rowBounds);
		
		return boardDTOList;
	}
	
	public BoardDTO getBoardInfo(int idx) {
		BoardDTO boardDTO = boardDAO.getBoardInfo(idx);
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
	
	public PageDTO getBoardCount(int board_id, int currentPage) {
		int contentCount = boardDAO.getBoardCount(board_id);
		PageDTO pageDTO = new PageDTO(contentCount, currentPage, this.page_listcount, this.page_pagenationcount);
		
		return pageDTO;
	}
	
}
