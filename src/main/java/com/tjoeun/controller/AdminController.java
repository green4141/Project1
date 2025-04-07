package com.tjoeun.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.AdminService;
import com.tjoeun.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminService adminService;
	@Resource(name = "loginUserDTO")
	private UserDTO loginUserDTO;
	
	@GetMapping("/user")
	public String user(@RequestParam(required = false, defaultValue = "1")int page,
			@RequestParam(required = false) String id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) Integer role,
      @RequestParam(required = false) String sort,
      @RequestParam(required = false) String order,
			Model model) {
		
		Map<String, Object> searchParam = new HashMap<>();
		
		if(!StringUtils.isBlank(id)) searchParam.put("id", id);
		else if(!StringUtils.isBlank(name)) searchParam.put("name", name);
		else if(!StringUtils.isBlank(username)) searchParam.put("username", username);
		else if(role != null) searchParam.put("role", role);
		
    if (!StringUtils.isBlank(sort)) searchParam.put("sort", sort);
    if (!StringUtils.isBlank(order)) searchParam.put("order", order);
		
		model.addAttribute("loginUserDTO", loginUserDTO);
		model.addAttribute("userList", adminService.getUserList(page, searchParam));
		model.addAttribute("pageDTO", adminService.getUserPageDTO(page, searchParam));
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("username", username);
		model.addAttribute("role", role);
    model.addAttribute("sort", sort);
    model.addAttribute("order", order);
		
		return "admin/userlist";
	}
	
	@GetMapping("/userdetail")
	public String userDetail(@RequestParam int idx, @ModelAttribute("joinUserDTO")UserDTO userDTO, Model model) {
		model.addAttribute("user", adminService.getUserByIdx(idx));
		return "admin/userdetail";
	}
	
	@PostMapping("/updateproc")
	public String userDetailProc(@ModelAttribute("joinUserDTO") UserDTO userDTO, Model model) {
		model.addAttribute("idx", userDTO.getIdx());
		model.addAttribute("work", "userUpdate");
		adminService.userUpdate(userDTO);
		return "admin/success";
	}
	
	@GetMapping("/userdelete")
	public String userdelete(@RequestParam int idx, Model model) {
		adminService.deleteUser(idx);
		model.addAttribute("work", "userDelete");
		return "admin/success";
	}
	@GetMapping("/board")
	public String board(@RequestParam(required = false, defaultValue = "1")int page,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) Long startdate,
			@RequestParam(required = false) Long enddate,
      @RequestParam(required = false) String sort,
      @RequestParam(required = false) String order,
			Model model) {
		Map<String, Object> searchParam = new HashMap<>();
		if(!StringUtils.isBlank(title)) {
			searchParam.put("title", title);
			model.addAttribute("title", title);
		}
		if(!StringUtils.isBlank(username)) {
			searchParam.put("username", username);
			model.addAttribute("username", username);
		}
		if(startdate != null) {
			searchParam.put("startdate", new Date(startdate));
			searchParam.put("enddate", new Date(enddate));
			model.addAttribute("startdate", startdate);
			model.addAttribute("enddate", enddate);
		}
    if (!StringUtils.isBlank(sort)) searchParam.put("sort", sort);
    if (!StringUtils.isBlank(order)) searchParam.put("order", order);
		 
		model.addAttribute("loginUserDTO", loginUserDTO);
		model.addAttribute("boardDTOList", adminService.getBoardList(page, searchParam));
		model.addAttribute("pageDTO", adminService.getBoardPageDTO(page, searchParam));
    model.addAttribute("sort", sort);
    model.addAttribute("order", order);
		return "admin/boardlist";
	}
	@GetMapping("/read")
	public String read(@RequestParam("idx") int idx,
			@RequestParam("page") int page,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) Long startdate,
			@RequestParam(required = false) Long enddate,
			Model model) {
		if(!StringUtils.isBlank(title)) model.addAttribute("title", title);
		if(!StringUtils.isBlank(username)) model.addAttribute("username", username);
		if(startdate != null) {
			model.addAttribute("startdate", startdate);
			model.addAttribute("enddate", enddate);
		}
		BoardDTO readBoardDTO = adminService.getBoardInfo(idx);
		model.addAttribute("idx", idx);
		model.addAttribute("readBoardDTO", readBoardDTO);
		model.addAttribute("loginUserDTO", loginUserDTO);
		model.addAttribute("page", page);
		return "admin/read";
	}
	@GetMapping("/delete")
	public String deleteBoard(@RequestParam int idx, Model model) {
		adminService.deleteBoard(idx);
		model.addAttribute("work", "boardDelete");
		return "admin/success";
	}
}
