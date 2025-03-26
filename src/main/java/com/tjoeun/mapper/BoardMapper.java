package com.tjoeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tjoeun.dto.BoardDTO;

public interface BoardMapper {
	@Insert("insert into tjoeun.board(board_id, title, content, user, file) " +
					"values(#{board_id}, #{title}, #{content}, #{user}, #{file})")
		void addBoardInfo(BoardDTO writeBoardDTO);
	

	@Select("SELECT name " + 
					"FROM boardinfo " + 
					"WHERE board_id=#{board_id}")
	String getBoardInfoName(int board_id);
	
	@Select("SELECT * FROM v_board_user " +
					"WHERE board_id = #{board_id} " +
					"ORDER BY idx DESC")
	List<BoardDTO> getBoardList(int board_id);
	
	@Select("select * from tjoeun.v_board_user where idx=#{idx}")
	BoardDTO getBoardInfo(int idx);
	
	@Update("UPDATE board SET hits = #{hits} WHERE idx = #{idx}")
	void updateHits(@Param("hits") int hits, @Param("idx") int idx);

	@Update("UPDATE board " +
					"SET title = #{title}, " +
					"content = #{content}, " +
					"file = #{file, jdbcType=VARCHAR} " +
					"WHERE idx = #{idx}")
	void modifyBoardInfo(BoardDTO modifyBoardDTO);
}
