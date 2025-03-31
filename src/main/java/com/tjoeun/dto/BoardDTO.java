package com.tjoeun.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {
	private int idx;
	private int board_id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	private Date date;
	private int user;
	private String file;
	private int hits;
	
	private MultipartFile upload_file;
	
	private String username;
	
	private boolean exist_favorite;
	
	private int favorite;
}
