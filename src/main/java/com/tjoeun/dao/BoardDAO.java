package com.tjoeun.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.dto.BoardDTO;
import com.tjoeun.mapper.BoardMapper;

@Repository
public class BoardDAO {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public void addBoardInfo(BoardDTO writeBoardDTO) {
		boardMapper.addBoardInfo(writeBoardDTO);
	}
	
	public String getBoardInfoName(int board_id) {
		String name = boardMapper.getBoardInfoName(board_id);
		return name;
	}
	
	public List<BoardDTO> getBoardList(int board_id){
		List<BoardDTO> boardDTOList = boardMapper.getBoardList(board_id);
		return boardDTOList;
	}
	
	public BoardDTO getBoardInfo(int idx) {
		BoardDTO boardDTO = boardMapper.getBoardInfo(idx);
		boardMapper.updateHits(boardDTO.getHits()+1, boardDTO.getIdx());
		return boardDTO;
	}
	
	public void modifyBoardInfo(BoardDTO modifyBoardDTO) {
		boardMapper.modifyBoardInfo(modifyBoardDTO);
	}
	
	public void deleteBoardInfo(int idx) {
		boardMapper.deleteBoardInfo(idx);
	}
}
