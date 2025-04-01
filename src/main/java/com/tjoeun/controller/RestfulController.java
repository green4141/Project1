package com.tjoeun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tjoeun.service.BoardService;
import com.tjoeun.service.UserService;
//사용자 ID 중복 체크
@RestController
public class RestfulController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/user/checkUserId/{id}")
	public String checkUserId(@PathVariable String id){
		boolean chk = userService.checkUserId(id);
		return chk + "";
	}
	
	@GetMapping("/board/toggleFavBoard")
	public boolean toggleFavBoard(@RequestParam int board_idx) {
		return boardService.toggleFavBoard(board_idx);
	}
}
