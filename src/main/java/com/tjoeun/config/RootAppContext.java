package com.tjoeun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.tjoeun.dto.UserDTO;

@Configuration
public class RootAppContext {
	@Bean("loginUserDTO")
	@SessionScope
	public UserDTO loginUserDTO() {
		return new UserDTO();
	}
}
