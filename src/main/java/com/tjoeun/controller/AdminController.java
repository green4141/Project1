package com.tjoeun.controller;

import javax.annotation.Resource;

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
	private final BoardService boardService;
	@Resource(name = "loginUserDTO")
	private UserDTO loginUserDTO;
	
	@GetMapping("/user")
	public String user(@RequestParam(required = false, defaultValue = "1")int page, Model model) {

		model.addAttribute("loginUserDTO", loginUserDTO);
		model.addAttribute("userList", adminService.getAllUserList(page));
		model.addAttribute("pageDTO", adminService.getUserPageDTO(page));
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
	public String board(@RequestParam(required = false, defaultValue = "1")int page, Model model) {

		model.addAttribute("loginUserDTO", loginUserDTO);
		model.addAttribute("boardDTOList", adminService.getAllBoardList(page));
		model.addAttribute("pageDTO", adminService.getBoardPageDTO(page));
		return "admin/boardlist";
	}
	@GetMapping("/read")
	public String read(@RequestParam("idx") int idx,
			               @RequestParam("page") int page, Model model) {
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
