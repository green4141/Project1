package com.tjoeun.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tjoeun.dao.TopMenuDAO;
import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.BoardInfoDTO;

import com.tjoeun.service.MainService;

@Controller
public class MainController {

	@Autowired
	private MainService mainService;
	
	@Autowired
	TopMenuDAO topMenuDAO;	
	
	@GetMapping("/main")
	public String main(Model model) {
		
		List<BoardDTO> boardDTOList = mainService.getMainList(0);
		
		List<BoardInfoDTO> boardInfoList = topMenuDAO.getTopMenuList(); 
		 
		model.addAttribute("boardDTOList", boardDTOList);
		model.addAttribute("boardInfoList", boardInfoList);
		
		return "main";

	}
	

}
