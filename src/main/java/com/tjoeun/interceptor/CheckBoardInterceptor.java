package com.tjoeun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.dto.UserDTO;

public class CheckBoardInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String parameter = request.getParameter("board_id");
		if("1".equals(parameter)) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/board/not_teacher");
			return false;
		}
		return true;
	}
}
