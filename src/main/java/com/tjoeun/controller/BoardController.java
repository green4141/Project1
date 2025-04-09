package com.tjoeun.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.PageDTO;
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
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) Long startdate,
			@RequestParam(required = false) Long enddate,
      @RequestParam(required = false) String sort,
      @RequestParam(required = false) String order,
			Model model) {
		Map<String, Object> searchParam = new HashMap<>();
		if (!StringUtils.isBlank(title)) {
			String decodedTitle = boardService.decodeBase64(title);
			String escapedTitle = boardService.escapeForLikeQuery(decodedTitle);
			searchParam.put("title", escapedTitle);
			model.addAttribute("title", title);
		}

		if (!StringUtils.isBlank(username)) {
			String decodedUsername = boardService.decodeBase64(username);
			String escapedUsername = boardService.escapeForLikeQuery(decodedUsername);
			searchParam.put("username", escapedUsername);
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
		
		List<BoardDTO> topNotices = boardService.getTopNotices(board_id);
		model.addAttribute("topNotices", topNotices);
		
		int normalCount = 10 - topNotices.size(); // 최대 10개까지 표시되도록 조절
		
		String name = boardService.getBoardInfoName(board_id);
		List<BoardDTO> boardDTOList = boardService.getBoardList(board_id, page, searchParam, normalCount);

		PageDTO pageDTO = boardService.getBoardCount(board_id, page, searchParam);
		
		model.addAttribute("board_id", board_id);
		model.addAttribute("name", name);
		model.addAttribute("boardDTOList", boardDTOList);
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("page", page);
	    model.addAttribute("sort", sort);
	    model.addAttribute("order", order);
		
		return "board/main";
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("board_id") int board_id,
			@RequestParam("idx") int idx,
			@RequestParam("page") int page,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) Long startdate,
			@RequestParam(required = false) Long enddate,
			Model model) {
		BoardDTO readBoardDTO = boardService.getBoardInfo(idx, loginUserDTO.getIdx());
		model.addAttribute("board_id", board_id);
		model.addAttribute("idx", idx);
		model.addAttribute("readBoardDTO", readBoardDTO);
		model.addAttribute("loginUserDTO", loginUserDTO);
		model.addAttribute("page", page);
		model.addAttribute("hasFile", boardService.isBoardHasFile(idx));
		if(title != null) model.addAttribute("title", title);
		if(username != null) model.addAttribute("username", username);
		if(startdate != null) {
			model.addAttribute("startdate", startdate);
			model.addAttribute("enddate", enddate);
		}
		return "board/read";
	}
	@GetMapping(value = "/requestimage", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	@ResponseBody
	public byte[] requestImage(@RequestParam int board_idx) {
		try {
			return boardService.loadUploadFile(board_idx);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/write")
	public String write(@ModelAttribute("writeBoardDTO") BoardDTO writeBoardDTO,
			@RequestParam("board_id") int board_id) {
		writeBoardDTO.setBoard_id(board_id);
			return "board/write";
	}
	
	@PostMapping("/writeProcedure")
	public String writeProcedure(@Valid @ModelAttribute("writeBoardDTO") BoardDTO writeBoardDTO,
			BindingResult result, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page) throws IOException {
		if(result.hasErrors()) {
			return "board/write";
		}
		
    try {
        boardService.addBoardInfo(writeBoardDTO);
    } catch (IllegalArgumentException e) {
        model.addAttribute("errorMessage", e.getMessage());
        return "board/write";
    }

		model.addAttribute("page", page);

		return "board/write_success";
	}

	@GetMapping("/modify")
	public String modify(@RequestParam("board_id") int board_id,
			@RequestParam("idx") int idx,
			@RequestParam("page") int page,
			@ModelAttribute("modifyBoardDTO") BoardDTO modifyBoardDTO,
			Model model) {
		BoardDTO tmpBoardDTO = boardService.getBoardInfo(idx, loginUserDTO.getIdx());

		modifyBoardDTO.setUsername(tmpBoardDTO.getUsername());
		modifyBoardDTO.setDate(tmpBoardDTO.getDate());
		modifyBoardDTO.setContent(tmpBoardDTO.getContent());
		modifyBoardDTO.setTitle(tmpBoardDTO.getTitle());
		modifyBoardDTO.setFile(tmpBoardDTO.getFile());
		modifyBoardDTO.setUser(tmpBoardDTO.getUser());
		modifyBoardDTO.setBoard_id(board_id);
		
		model.addAttribute("board_id", board_id);
		model.addAttribute("idx", idx);
		model.addAttribute("page", page);
		model.addAttribute("hasFile", boardService.isBoardHasFile(idx));
		return "board/modify";
	}
	
	@PostMapping("/modifyProcedure")
	public String modifyProcedure(@Valid @ModelAttribute("modifyBoardDTO") BoardDTO modifyBoardDTO,
			BindingResult result,
			Model model,
			@RequestParam("page") int page) throws IOException {
		model.addAttribute("page", page);
		model.addAttribute("idx", modifyBoardDTO.getIdx());
		model.addAttribute("board_id", modifyBoardDTO.getBoard_id());
		if(result.hasErrors()) {
			BoardDTO originalBoardDTO = boardService.getBoardInfo(modifyBoardDTO.getIdx(), loginUserDTO.getIdx());
			modifyBoardDTO.setDate(originalBoardDTO.getDate());
			modifyBoardDTO.setUsername(originalBoardDTO.getUsername());
			model.addAttribute("hasFile", boardService.isBoardHasFile(modifyBoardDTO.getIdx()));
			model.addAttribute("modifyBoardDTO", modifyBoardDTO);
			
			return "board/modify";
		}
		
    try {
      	boardService.modifyBoardInfo(modifyBoardDTO);
	  } catch (IllegalArgumentException e) {
	      model.addAttribute("errorMessage", e.getMessage());
	      return "board/modify";
	  }
		
		boardService.modifyBoardInfo(modifyBoardDTO);
		return "board/modify_success";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("board_id") int board_id,
			@RequestParam("idx") int idx,
			Model model) {
		boardService.deleteBoardInfo(idx);
		model.addAttribute("board_id", board_id);
		return "board/delete";
	}

	@GetMapping("/not_teacher")
	public String notTeacher() {		
		return "board/not_teacher";
	}
	
	@GetMapping("/not_writer")
	public String notWriter() {
		return "board/not_writer";
	}
}
