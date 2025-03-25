package com.tjoeun.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.UserDTO;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Value("${path.upload}")
	private String path_upload;
	
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
	
	public void addBoardInfo(BoardDTO writeBoardDTO) {
		
		MultipartFile upload_file = writeBoardDTO.getUpload_file();
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			System.out.println("파일이름 : " + file_name);
			
			writeBoardDTO.setFile(file_name);
		}
		writeBoardDTO.setUser(loginUserDTO.getIdx());
		
		boardDAO.addBoardInfo(writeBoardDTO);
	}
	
	public String getBoardInfoName(int board_id) {
		String name = boardDAO.getBoardInfoName(board_id);
		return name;
	}

	public List<BoardDTO> getBoardList(int board_id){
		List<BoardDTO> boardDTOList = boardDAO.getBoardList(board_id);
		return boardDTOList;
	}
	
	public BoardDTO getBoardInfo(int idx) {
		BoardDTO boardDTO = boardDAO.getBoardInfo(idx);
		return boardDTO;
	}
	
}
