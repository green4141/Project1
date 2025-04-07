package com.tjoeun.mapper;

import org.apache.ibatis.annotations.*;

import com.tjoeun.dto.FileDTO;

public interface FileMapper {
	@Insert("insert into file(originalname, servername, board_idx) values(#{originalname}, #{servername}, #{board_idx}")
	void insert(FileDTO fileDTO);
	
	@Select("select * from file where board_idx = #{board_idx}")
	FileDTO findByBoardIdx(int board_idx);
	
	@Update("update file set originalname = #{originalname}, servername = #{servername} where idx = #{idx}")
	void update(FileDTO fileDTO);
	
	@Delete("delete from file where idx = #{idx}")
	void deleteByIdx(int idx);
}
