package com.tjoeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.tjoeun.dto.BoardDTO;

public interface BoardMapper {
	@Insert("insert into tjoeun.board(board_id, title, content, user, file) " +
					"values(#{board_id}, #{title}, #{content}, #{user}, #{file})")
		void addBoardInfo(BoardDTO writeBoardDTO);
	

	@Select("SELECT name " + 
					"FROM boardinfo " + 
					"WHERE board_id=#{board_id}")
	String getBoardInfoName(int board_id);
	
	@Select("SELECT * FROM v_board_user "
			+ "WHERE board_id = #{board_id} "
			+ "ORDER BY idx DESC")
	List<BoardDTO> getBoardList(int board_id);

}
