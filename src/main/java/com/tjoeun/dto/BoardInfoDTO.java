package com.tjoeun.dto;

import lombok.Data;

@Data
public class BoardInfoDTO {
	private int idx;
	// 0이면 자유게시판 1이면 선생게시판
	private int board_id;
	private String name;
	
}
