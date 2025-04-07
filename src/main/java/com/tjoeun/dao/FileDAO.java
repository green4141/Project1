package com.tjoeun.dao;

import org.springframework.stereotype.Repository;
import com.tjoeun.dto.FileDTO;
import com.tjoeun.mapper.FileMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FileDAO {
	private final FileMapper fileMapper;
	
	public void insert(FileDTO fileDTO) {
		fileMapper.insert(fileDTO);
	}
	
	public void update(FileDTO fileDTO) {
		fileMapper.update(fileDTO);
	}
	
	public FileDTO findByBoardIdx(int board_idx) {
		return fileMapper.findByBoardIdx(board_idx);
	}
	
	public void delete(int idx) {
		fileMapper.delete(idx);

	}
}
