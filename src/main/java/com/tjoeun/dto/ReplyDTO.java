package com.tjoeun.dto;

import lombok.Data;

@Data
public class ReplyDTO {
	private int idx;
	private int user_idx;
	private int board_idx;
	private String content;
	private String username;
}
