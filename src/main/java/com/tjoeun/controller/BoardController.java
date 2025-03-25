package com.tjoeun.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.dto.BoardDTO;
import com.tjoeun.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/main")
	public String main(@RequestParam("board_id") int board_id,
										 Model model) {
		
		model.addAttribute("board_id", board_id);
		
		return "board/main";
	}
	
	@GetMapping("/read")
	public String read() {
		return "board/read";
	}
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeBoardDTO") BoardDTO writeBoardDTO,
											@RequestParam("board_id") int board_id) {
		writeBoardDTO.setBoard_id(board_id);
		return "board/write";
	}
	
	@PostMapping("/writeProcedure")
	public String writeProcedure(@Valid @ModelAttribute("writeBoardDTO") BoardDTO writeBoardDTO,
															 BindingResult result) {
		if(result.hasErrors()) {
			return "board/write";
		}
		
		boardService.addBoardInfo(writeBoardDTO);
		
		return "board/write_success";
	}
	@GetMapping("/modify")
	public String modify() {
		return "board/modify";
	}
	@GetMapping("/delete")
	public String delete() {
		return "board/delete";
	}
}
