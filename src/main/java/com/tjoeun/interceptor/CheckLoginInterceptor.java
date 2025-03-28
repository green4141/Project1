package com.tjoeun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.dto.UserDTO;

public class CheckLoginInterceptor implements HandlerInterceptor{
	
	private UserDTO loginUserDTO;
	
	public CheckLoginInterceptor(UserDTO loginUserDTO) {
		this.loginUserDTO = loginUserDTO;
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		String parameter = request.getParameter("board_id");
		
		if(loginUserDTO.isUserLogin() == false) {
			if(url.contains("/main") || url.contains("/read")) {
				if("0".equals(parameter)) {
					return true;
				} else {
					String contextPath = request.getContextPath();
					response.sendRedirect(contextPath + "/user/cannot_login");
					return false;
				}
			}else{
				String contextPath = request.getContextPath();
				response.sendRedirect(contextPath + "/user/cannot_login");
				return false;
			}
		}
		return true;
	}
  
}








