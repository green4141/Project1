package com.tjoeun.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tjoeun.dao.ReplyDAO;
import com.tjoeun.dto.ReplyDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	private final ReplyDAO replyDAO;
	public void insert(ReplyDTO replyDTO) {
		replyDAO.insert(replyDTO);
	}
	public List<ReplyDTO> select(int board_idx) {
		return replyDAO.select(board_idx);
	}
	public void update(ReplyDTO replyDTO) {
		replyDAO.update(replyDTO);
	}
	public void delete(int idx) {
		replyDAO.delete(idx);
	}
}
