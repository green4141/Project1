package com.tjoeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.tjoeun.dto.BoardDTO;

public interface BoardMapper {
	@Insert("insert into tjoeun.board(board_id, title, content, user, file) " +
					"values(#{board_id}, #{title}, #{content}, 5, #{file})")
		void addBoardInfo(BoardDTO writeBoardDTO);
	
	/*
	@Select("SELECT name " + 
					"FROM boardinfo " + 
					"WHERE board_id=#{board_id}")
	String getBoardInfoName(int board_id);
	
	@Select("SELECT c.content_idx, c.content_subject, u.user_name content_writer_name, "+
		  "       to_char(c.content_date, 'YYYY-MM-DD') content_date "+
		  "  FROM content_table c, user_table u "+
		  " WHERE c.content_writer_idx = u.user_idx "+
		  "   AND c.content_board_idx = #{board_id} "+
		  "ORDER BY c.content_idx desc")	
	List<BoardDTO> getBoardList(int board_id);
	*/
}
