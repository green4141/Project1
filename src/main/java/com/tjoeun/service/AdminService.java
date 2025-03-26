package com.tjoeun.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.dao.UserDAO;
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

	public List<UserDTO> getAllUserList(int page) {
		int start = (page - 1) * page_listcount;
		RowBounds rowBounds = new RowBounds(start, page_listcount);
		return userDAO.getAllUserList(rowBounds);
	}
}
