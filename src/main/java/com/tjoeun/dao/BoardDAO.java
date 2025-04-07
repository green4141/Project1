package com.tjoeun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.FavoriteDTO;
import com.tjoeun.mapper.BoardMapper;

@Repository
public class BoardDAO {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public int addBoardInfo(BoardDTO writeBoardDTO) {
		return boardMapper.addBoardInfo(writeBoardDTO);
	}
	
	public String getBoardInfoName(int board_id) {
		String name = boardMapper.getBoardInfoName(board_id);
		return name;
	}
	
	public List<BoardDTO> getBoardList(int board_id, RowBounds rowBounds, Map<String, Object> searchParam) {
		if(searchParam.isEmpty()) return boardMapper.getBoardList(board_id, rowBounds);
		else {
			
			return boardMapper.searchBoardList(rowBounds, searchParam);
		}
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

	public int getBoardCount(int board_id, Map<String, Object> searchParamMap) {
		int boardPageCount;
		if(searchParamMap.isEmpty()) boardPageCount = boardMapper.getBoardCount(board_id);
		else {
			searchParamMap.put("board_id", board_id);
			boardPageCount = boardMapper.searchBoardCount(searchParamMap);
		}
		return boardPageCount;
	}
	
	public List<BoardDTO> getAllBoardList(RowBounds rowBounds) {
		return boardMapper.getAllUserInfo(rowBounds);
	}
	
	public int getAllBoardCount() {
		return boardMapper.getAllUserCount();
	}
	

	public boolean isFavBoardExists(int user_idx, int board_idx) {
    return boardMapper.isFavBoardExists(user_idx, board_idx) > 0;
  }
	

	public int addFavBoard(FavoriteDTO favBoardDTO) {
		return boardMapper.addFavBoard(favBoardDTO);
	}
	
	public void deleteFavBoard(int user_idx, int board_idx) {
        boardMapper.deleteFavBoard(user_idx, board_idx);
    }
	public List<BoardDTO> getAdminBoardList(RowBounds rowbounds, Map<String, Object> searchParam) {
		return boardMapper.getAdminBoardList(rowbounds, searchParam);
	}
	public int getAdminBoardCount(Map<String, Object> searchParam) {
		return boardMapper.getAdminBoardCount(searchParam);
	}

	public void updateNoticeStatus(int idx, int isNoticeValue) {
		boardMapper.updateNoticeStatus(idx, isNoticeValue);
	}
	
	public List<BoardDTO> getTopNotices(int board_id) {
		return boardMapper.getTopNotices(board_id);
	}
	
	public List<BoardDTO> getGeneralBoardListExcludingNotices(RowBounds rowBounds) {
	    return boardMapper.getGeneralBoardListExcludingNotices(rowBounds);
	}
	
	public int getGeneralBoardCount() {
	    return boardMapper.getGeneralBoardCount();
	}
	public List<BoardDTO> getSortedBoard(RowBounds rowBounds, Map<String, Object> paramMap){
		return boardMapper.getSortedBoard(rowBounds, paramMap);
	}

}
