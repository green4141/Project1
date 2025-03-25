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
	
	@Select("SELECT b.idx, b.title, u.name writer_name, "+
		  		"to_char(b.date, 'YYYY-MM-DD') date "+
		  		"FROM board b, user u "+
		  		"WHERE b.user = u.idx "+
		  		"AND b.board_id = #{board_id} "+
		  		"ORDER BY b.idx desc")	
	List<BoardDTO> getBoardList(int board_id);

}
