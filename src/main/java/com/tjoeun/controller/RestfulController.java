package com.tjoeun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tjoeun.service.UserService;
//사용자 ID 중복 체크
@RestController
public class RestfulController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/user/checkUserId/{id}")
	public String checkUserId(@PathVariable String id){
		boolean chk = userService.checkUserId(id);
		return chk + "";
	}
	
}
