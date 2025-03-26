package com.tjoeun.controller;

import java.util.List;

import javax.annotation.Resource;
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
import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	@GetMapping("/main")
	public String main(@RequestParam("board_id") int board_id,
			               Model model) {
		String name = boardService.getBoardInfoName(board_id);
		List<BoardDTO> boardDTOList = boardService.getBoardList(board_id);
		
		model.addAttribute("board_id", board_id);
		model.addAttribute("name", name);
		model.addAttribute("boardDTOList", boardDTOList);
		
		return "board/main";
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("board_id") int board_id,
										 @RequestParam("idx") int idx, Model model) {
		BoardDTO readBoardDTO = boardService.getBoardInfo(idx);
		model.addAttribute("board_id", board_id);
		model.addAttribute("idx", idx);
		model.addAttribute("readBoardDTO", readBoardDTO);
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
	
	@GetMapping("/not_teacher")
	public String not_teacher() {		
		return "board/not_teacher";
	}
}
