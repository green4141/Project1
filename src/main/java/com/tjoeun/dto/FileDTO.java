package com.tjoeun.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
	private int idx;
	private String originalname;
	private String servername;
	private int board_idx;
	private MultipartFile upload_file;
}
