package com.tjoeun.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.AdminService;
import com.tjoeun.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminService adminService;
	@Resource(name = "loginUserDTO")
	private UserDTO loginUserDTO;
	
	@GetMapping("/user")
	public String user(@RequestParam(required = false, defaultValue = "1")int page, Model model) {
		// TODO: user role check, if(!=2) reject
		model.addAttribute("loginUserDTO", loginUserDTO);
		model.addAttribute("userList", adminService.getAllUserList(page));
		return "admin/userlist";
	}
	
	@GetMapping("/userdetail")
	public String userDetail(@ModelAttribute("loginUserDTO")UserDTO userDTO, Model model) {
		// TODO: load target user dto
		model.addAttribute("targetUser", userDTO);
		return "admin/userdetail";
	}
	
	@PostMapping("/userdetailproc")
	public String userDetailProc(@ModelAttribute("targetUserDTO") UserDTO userDTO, Model model) {
		//TODO: update user
		return "admin/success";
	}
}
