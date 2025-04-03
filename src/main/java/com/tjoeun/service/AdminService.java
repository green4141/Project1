package com.tjoeun.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.dao.UserDAO;
import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.PageDTO;
import com.tjoeun.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@PropertySource("/WEB-INF/properties/option.properties")
public class AdminService {
	private final UserDAO userDAO;
	private final BoardDAO boardDAO;
	@Value("${page.listcount}")
	private int page_listcount;

	@Value("${page.pagenationcount}")
	private int pagenation_count;
	
	public List<UserDTO> getUserList(int page, Map<String, Object> searchParam) {
		int start = (page - 1) * page_listcount;
		RowBounds rowBounds = new RowBounds(start, page_listcount);
		
		return userDAO.getUserList(rowBounds, searchParam);
	}
	
	public PageDTO getUserPageDTO(int page, Map<String, Object> searchParam) {
		int userCount = userDAO.getUserCount(searchParam);
		return new PageDTO(userCount, page, page_listcount, pagenation_count);
	}
	
	public UserDTO getUserByIdx(int idx) {
		return userDAO.getModifyUserInfo(idx);
	}
	public void userUpdate(UserDTO dto) {
		userDAO.userUpdate(dto);
	}
	public void deleteUser(int idx) {
		userDAO.deleteUser(idx);
	}
	public List<BoardDTO> getAllBoardList(int page) {
		int start = (page - 1) * page_listcount;
		RowBounds rowBounds = new RowBounds(start, page_listcount);
		return boardDAO.getAllBoardList(rowBounds);
	}
	public PageDTO getBoardPageDTO(int page) {
		int boardCount = boardDAO.getAllBoardCount();
		return new PageDTO(boardCount, page, page_listcount, pagenation_count);
	}
	public void deleteBoard(int idx) {
		boardDAO.deleteBoardInfo(idx);
	}
	public BoardDTO getBoardInfo(int idx) {
		BoardDTO boardDTO = boardDAO.getBoardInfo(idx);
		return boardDTO;
	}
}
