package com.tjoeun.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.dto.BoardDTO;

@Service
public class MainService {
	
	@Autowired
	private BoardDAO boardDAO;

	public List<BoardDTO> getMainList(int board_id){
		RowBounds rowBounds = new RowBounds(0, 5);
		List<BoardDTO> boardDTOList = 
				boardDAO.getBoardList(board_id, rowBounds);
		return boardDTOList;		
	}
	
}