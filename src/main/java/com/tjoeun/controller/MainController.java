package com.tjoeun.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tjoeun.dao.TopMenuDAO;
import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.BoardInfoDTO;
import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.MainService;

@Controller
public class MainController {

	@Autowired
	private MainService mainService;
	
	@Autowired
	TopMenuDAO topMenuDAO;	
	
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	@GetMapping("/main")
	public String main(Model model) {
		
		List<List<BoardDTO>> boardDTOList = new ArrayList<>();
		boardDTOList.add(mainService.getMainList(0));
		if(loginUserDTO.getRole() == 1 || loginUserDTO.getRole() == 2) boardDTOList.add(mainService.getMainList(1));
		List<BoardInfoDTO> boardInfoList = topMenuDAO.getTopMenuList(); 
		 
		model.addAttribute("boardDTOList", boardDTOList);
		model.addAttribute("boardInfoList", boardInfoList);
		
		return "main";

	}
	

}
