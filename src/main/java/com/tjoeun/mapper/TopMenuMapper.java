package com.tjoeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tjoeun.dto.BoardInfoDTO;

public interface TopMenuMapper {
	@Select("SELECT * FROM boardinfo " + 
					"ORDER BY board_id")
	List<BoardInfoDTO> getTopMenuList();
}
