package com.tjoeun.interceptor;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.dto.UserDTO;

public class AdminInterceptor implements HandlerInterceptor{
	private UserDTO loginUserDTO;
	public AdminInterceptor(UserDTO loginUserDTO) {
		this.loginUserDTO = loginUserDTO;
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(loginUserDTO.isUserLogin() && loginUserDTO.getRole() == 2) return true;
		response.sendRedirect("/error?error=adminloginerror");
		return false;
		
	}
}
