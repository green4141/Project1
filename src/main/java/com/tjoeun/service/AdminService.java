package com.tjoeun.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.dao.FileDAO;
import com.tjoeun.dao.UserDAO;
import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.FileDTO;
import com.tjoeun.dto.PageDTO;
import com.tjoeun.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@PropertySource("/WEB-INF/properties/option.properties")
public class AdminService {
	private final UserDAO userDAO;
	private final BoardDAO boardDAO;
	private final FileDAO fileDAO;
	
	@Value("${page.adminlistcount}")
	private int page_adminlistcount;
	
	@Value("${page.listcount}")
	private int page_listcount;

	@Value("${page.pagenationcount}")
	private int pagenation_count;
	
	public PageDTO getUserPageDTO(int page, Map<String, Object> searchParam) {
		int userCount = userDAO.getUserCount(searchParam);
		return new PageDTO(userCount, page, page_adminlistcount, pagenation_count);
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

	    // 공지사항은 항상 가져옴
	    List<BoardDTO> freeNotices = boardDAO.getTopNotices(0);
	    List<BoardDTO> teacherNotices = boardDAO.getTopNotices(1);
	    List<BoardDTO> result = new java.util.ArrayList<>();
	    result.addAll(freeNotices);
	    result.addAll(teacherNotices);

	    int noticeCount = result.size();
	    int generalPageSize = page_adminlistcount - noticeCount;
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
	public boolean isBoardHasFile(int board_idx) {
		return fileDAO.findByBoardIdx(board_idx) != null;
	}
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
	    
	    int generalPageSize = page_listcount - noticeCount;  // 일반글 기준 페이지당 글 수
	    if (generalPageSize <= 0) generalPageSize = 1;

	    return new PageDTO(boardCount, page, page_adminlistcount, pagenation_count);
	}
	public void deleteBoard(int idx) {
		if(isBoardHasFile(idx)) {
			FileDTO fileDTO = fileDAO.findByBoardIdx(idx);
			File file = new File(fileDTO.getServername());
			file.delete();
			fileDAO.delete(fileDTO.getIdx());
		}
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
		int start = (page - 1) * page_adminlistcount;
		RowBounds rowBounds = new RowBounds(start, page_adminlistcount);

		if (searchParam.containsKey("id")) {
			String encoded = (String) searchParam.get("id");
			searchParam.put("id", decodeBase64(encoded));
		}
		if (searchParam.containsKey("name")) {
			String encoded = (String) searchParam.get("name");
			searchParam.put("name", decodeBase64(encoded));
		}
		if (searchParam.containsKey("username")) {
			String encoded = (String) searchParam.get("username");
			searchParam.put("username", decodeBase64(encoded));
		}
		
		if (searchParam.containsKey("sort") && searchParam.containsKey("order")) {
			return userDAO.getSortedUser(rowBounds, searchParam);
		}

		return userDAO.getUserList(rowBounds, searchParam);
	}


	public String decodeBase64(String value) {
		try {
			return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
		} catch (IllegalArgumentException e) {
			return value;
		}
	}
	
	public String escapeForLikeQuery(String keyword) {
		if (keyword == null) return null;
		return keyword
			.replace("\\", "\\\\")
			.replace("_", "\\_")
			.replace("%", "\\%");
	}
	
}