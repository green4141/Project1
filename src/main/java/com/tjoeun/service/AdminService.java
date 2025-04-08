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

	public List<BoardDTO> getBoardList(int page, Map<String, Object> searchParam) {
	    int pageSize = 10;

	    // 공지사항은 항상 가져옴
	    List<BoardDTO> freeNotices = boardDAO.getTopNotices(0);
	    List<BoardDTO> teacherNotices = boardDAO.getTopNotices(1);
	    List<BoardDTO> result = new java.util.ArrayList<>();
	    result.addAll(freeNotices);
	    result.addAll(teacherNotices);

	    int noticeCount = result.size();
	    int generalPageSize = pageSize - noticeCount;
	    if (generalPageSize <= 0) generalPageSize = 1;  // 최소 1개

	    // 공지사항은 페이징에 영향을 주지 않도록, 일반글만 독립적으로 페이징
	    int start = (page - 1) * generalPageSize;
	    RowBounds rowBounds = new RowBounds(start, generalPageSize);
	    
		//if (searchParam.containsKey("sort") && searchParam.containsKey("order")) {
			result.addAll( boardDAO.getSortedBoard(rowBounds, searchParam));
	//	}
	   // List<BoardDTO> generalList = boardDAO.getGeneralBoardListExcludingNotices(rowBounds);

	   // result.addAll(generalList);
	    return result;
	}
	/*
	public List<BoardDTO> getBoardList(int page, Map<String, Object> searchParam) {
		int start = (page - 1) * page_listcount;
		RowBounds rowBounds = new RowBounds(start, page_listcount);
		
		if (searchParam.containsKey("sort") && searchParam.containsKey("order")) {
			return boardDAO.getSortedBoard(rowBounds, searchParam);
		}
		
		return boardDAO.getAdminBoardList(rowBounds, searchParam);
	}*/
	
	public PageDTO getBoardPageDTO(int page, Map<String, Object> searchParam) {
	    int boardCount;
	    boolean hasSearch = searchParam != null && (
	        searchParam.containsKey("title") ||
	        searchParam.containsKey("username") ||
	        searchParam.containsKey("startdate") // 날짜 검색도 포함
	    );

	    if (hasSearch) {
	        // 검색 조건이 있으면 조건을 기반으로 count
	        boardCount = boardDAO.getAdminBoardCount(searchParam);
	    } else {
	        // 검색 조건이 없으면 기존처럼 전체 일반글 count
	        boardCount = boardDAO.getGeneralBoardCount();
	    }
	    
	    int noticeCount = boardDAO.getTopNotices(0).size() + boardDAO.getTopNotices(1).size();
	    
	    int generalPageSize = 10 - noticeCount;  // 일반글 기준 페이지당 글 수
	    if (generalPageSize <= 0) generalPageSize = 1;

	    return new PageDTO(boardCount, page, page_listcount, pagenation_count);
	}
	public void deleteBoard(int idx) {
		boardDAO.deleteBoardInfo(idx);
	}
	public BoardDTO getBoardInfo(int idx) {
		BoardDTO boardDTO = boardDAO.getBoardInfo(idx);
		return boardDTO;
	}

	public void updateNoticeStatus(int idx, int isNoticeValue) {
		boardDAO.updateNoticeStatus(idx, isNoticeValue);
	}


	
	public List<UserDTO> getUserList(int page, Map<String, Object> searchParam) {
		int start = (page - 1) * page_listcount;
		RowBounds rowBounds = new RowBounds(start, page_listcount);

		if (searchParam.containsKey("sort") && searchParam.containsKey("order")) {
			return userDAO.getSortedUser(rowBounds, searchParam);
		}

		return userDAO.getUserList(rowBounds, searchParam);
	}


}
