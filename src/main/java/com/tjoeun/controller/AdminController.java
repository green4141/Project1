package com.tjoeun.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.AdminService;
import com.tjoeun.validator.AdminUserValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminService adminService;
	private final AdminUserValidator adminUserValidator;
	
	@Resource(name = "loginUserDTO")
	private UserDTO loginUserDTO;

	@GetMapping("/user")
	public String user(@RequestParam(required = false, defaultValue = "1")int page,
			@RequestParam(required = false) String id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) Integer search_role,
		    @RequestParam(required = false) String sort,
		    @RequestParam(required = false) String order,
			Model model) {
		
		Map<String, Object> searchParam = new HashMap<>();
		
		if(!StringUtils.isBlank(id)) searchParam.put("id", id);
		else if(!StringUtils.isBlank(name)) searchParam.put("name", name);
		else if(!StringUtils.isBlank(username)) searchParam.put("username", username);
		else if(search_role != null) searchParam.put("role", search_role);
		
	    if (!StringUtils.isBlank(sort)) searchParam.put("sort", sort);
	    if (!StringUtils.isBlank(order)) searchParam.put("order", order);
		
		model.addAttribute("loginUserDTO", loginUserDTO);
		model.addAttribute("userList", adminService.getUserList(page, searchParam));
		model.addAttribute("pageDTO", adminService.getUserPageDTO(page, searchParam));
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("username", username);
		model.addAttribute("role", search_role);
	    model.addAttribute("sort", sort);
	    model.addAttribute("order", order);
		
		return "admin/userlist";
	}
	
	@GetMapping("/userdetail")
	public String userDetail(@RequestParam int idx, @ModelAttribute("joinUserDTO")UserDTO userDTO,
			@RequestParam(required = false) String id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String search_role,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order,
			Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("username", username);
		model.addAttribute("search_role", search_role);
	    model.addAttribute("sort", sort);
	    model.addAttribute("order", order);
		model.addAttribute("user", adminService.getUserByIdx(idx));
		return "admin/userdetail";
	}
	
	@PostMapping("/updateproc") 
	public String userDetailProc(
	    @ModelAttribute("joinUserDTO") UserDTO userDTO,
		@RequestParam(required = false) String search_id,
		@RequestParam(required = false) String search_name,
		@RequestParam(required = false) String search_username,
		@RequestParam(required = false) String search_role,
		@RequestParam(required = false) String search_order,
		@RequestParam(required = false) String search_sort,
	    BindingResult bindingResult,
	    Model model) {
		
	    model.addAttribute("id", search_id);
		model.addAttribute("name", search_name);
		model.addAttribute("username", search_username);
		model.addAttribute("search_role", search_role);
	    model.addAttribute("sort", search_sort);
	    model.addAttribute("order", search_order);
	    adminUserValidator.validate(userDTO, bindingResult);
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("idx", userDTO.getIdx());
	        model.addAttribute("work", "userUpdate");
	        model.addAttribute("user", userDTO);
	        return "admin/userdetail";
	    }

	    adminService.userUpdate(userDTO);
	    model.addAttribute("idx", userDTO.getIdx());
	    model.addAttribute("work", "userUpdate");
	    return "admin/success";
	}
	
	@GetMapping("/userdelete")
	public String userdelete(@RequestParam int idx, Model model) {
		adminService.deleteUser(idx);
		model.addAttribute("work", "userDelete");
		return "admin/success";
	}
	@GetMapping("/board")
	public String board(@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) Long startdate,
			@RequestParam(required = false) Long enddate,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order,
			Model model) {

		Map<String, Object> searchParam = new HashMap<>();
		if (!StringUtils.isBlank(title)) {
			String decodedTitle = adminService.decodeBase64(title);
			String escapedTitle = adminService.escapeForLikeQuery(decodedTitle);
			searchParam.put("title", escapedTitle);
			model.addAttribute("title", title);
		}
		if (!StringUtils.isBlank(username)) {
			String decodedUsername = adminService.decodeBase64(username);
			String escapedUsername = adminService.escapeForLikeQuery(decodedUsername);
			searchParam.put("username", escapedUsername);
			model.addAttribute("username", username);
		}
		if (startdate != null) {
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
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order,
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
		model.addAttribute("hasFile", adminService.isBoardHasFile(idx));
		model.addAttribute("sort", sort);
		model.addAttribute("order", order);
		return "admin/read";
	}
	@GetMapping("/delete")
	public String deleteBoard(@RequestParam int idx, Model model) {
		adminService.deleteBoard(idx);
		model.addAttribute("work", "boardDelete");
		return "admin/success";
	}
	@PostMapping("/toggleNotice")
	public String toggleNotice(@RequestParam("idx") int idx,
			@RequestParam(value = "is_notice", required = false) String isNotice,
			@RequestParam("page") int page) {
	    // 체크박스 체크: isNotice="1", 체크 해제: null
	    int isNoticeValue = (isNotice != null) ? 1 : 0;
	    // 공지 상태 업데이트
	    adminService.updateNoticeStatus(idx, isNoticeValue);
	    // 해당 게시글 다시 보기 페이지로 이동
	    return "redirect:/admin/read?idx=" + idx + "&page=" + page;
	}
}
