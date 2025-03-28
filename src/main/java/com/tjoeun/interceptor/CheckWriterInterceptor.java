package com.tjoeun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor {
	private UserDTO loginUserDTO;
	private BoardService boardService;
	
	public CheckWriterInterceptor(UserDTO loginUserDTO, BoardService boardService) {
		this.loginUserDTO = loginUserDTO;
		this.boardService = boardService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String strIdx = request.getParameter("idx");
		int boardIdx = Integer.parseInt(strIdx);
		BoardDTO currentBoardDTO = boardService.getBoardInfo(boardIdx);
		if(currentBoardDTO.getUser() != loginUserDTO.getIdx()) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/board/not_writer");
			return false;
		}
		return true;
	}
}
