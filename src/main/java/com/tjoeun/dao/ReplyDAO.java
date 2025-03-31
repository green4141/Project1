package com.tjoeun.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tjoeun.dto.ReplyDTO;
import com.tjoeun.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
	private final ReplyMapper replyMapper;
	
	public int insert(ReplyDTO replyDTO) {
		return replyMapper.addReply(replyDTO);
	}
	
	public List<ReplyDTO> select(int board_idx) {
		return replyMapper.getReplyList(board_idx);
	}
	
	public void update(ReplyDTO replyDTO) {
		replyMapper.updateReply(replyDTO);
	}
	
	public void delete(int idx) {
		replyMapper.deleteReply(idx);
	}
}
