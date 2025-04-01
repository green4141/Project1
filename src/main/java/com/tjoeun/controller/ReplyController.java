package com.tjoeun.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tjoeun.dto.ReplyDTO;
import com.tjoeun.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {
	private final ReplyService replyService;

	@PostMapping("/insert")
	public int insertReply(@RequestBody ReplyDTO replyDTO) {
		replyService.insert(replyDTO);
		return replyDTO.getIdx();
	}
	@GetMapping("/select")
	public List<ReplyDTO> selectReply(@RequestParam int board_idx) {
		return replyService.select(board_idx);
	}
	@PostMapping("/update")
	public void updateReply(@RequestBody ReplyDTO replyDTO) {
		replyService.update(replyDTO);
	}
	@PostMapping("/delete")
	public void deleteReply(@RequestBody ReplyDTO dto) {
		replyService.delete(dto.getIdx());
	}
}
