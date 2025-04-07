package com.tjoeun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.tjoeun.dto.BoardDTO;
import com.tjoeun.dto.FavoriteDTO;
import com.tjoeun.dto.UserDTO;

public interface BoardMapper {
    @Insert("insert into tjoeun.board(board_id, title, content, user, file) " +
    "values(#{board_id}, #{title}, #{content}, #{user}, #{file})")
    @Options(useGeneratedKeys = true, keyProperty = "idx")
    int addBoardInfo(BoardDTO writeBoardDTO);

    @Select("SELECT name " + 
          "FROM boardinfo " + 
          "WHERE board_id=#{board_id}")
    String getBoardInfoName(int board_id);

    @Select("SELECT * FROM v_board_user " +
          "WHERE board_id = #{board_id} " +
          "ORDER BY idx DESC")
    List<BoardDTO> getBoardList(int board_id, RowBounds rowBounds);

    @Select({"<script>"
    		+ "SELECT * from v_board_user "
    		+ "<where>"
    		+ "<if test='title != null'>"
    		+ "title like concat('%',#{title},'%')"
    		+ "</if>"
    		+ "<if test='username != null'>"
    		+ "username = #{username}"
    		+ "</if>"
    		+ "<if test='startdate != null'>"
    		+ "date between #{startdate} and #{enddate}"
    		+ "</if>"
    		+ "and board_id = #{board_id} "
    		+ "</where>"
    		+ "</script>"})
    List<BoardDTO> searchBoardList(RowBounds rowBounds, Map<String, Object> paramMap);
    
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

    @Delete("DELETE FROM board WHERE idx = ${idx}")
    void deleteBoardInfo(int idx);

    @Select("SELECT count(*) " +
          "FROM board " +
          "WHERE board_id = #{board_id}")
    int getBoardCount(int board_id);
    
    @Select({"<script>"
    		+ "select count(*) from v_board_user "
    		+ "<where>"
    		+ "<if test='title != null'>"
    		+ "title like concat('%', #{title}, '%')"
    		+ "</if>"
    		+ "<if test='username != null'>"
    		+ "username = #{username}"
    		+ "</if>"
    		+ "<if test='startdate != null'>"
    		+ "date between #{startdate} and #{enddate}"
    		+ "</if>"
    		+ "and board_id = #{board_id} "
    		+ "</where>"
        + "ORDER BY "
        + "  <choose>"
        + "    <when test='sort != null and order != null'> ${sort} ${order} </when>"
        + "    <otherwise> idx DESC </otherwise>"
        + "  </choose>"
        + "</script>"})
    int searchBoardCount(Map<String, Object> searchparam);

    @Select("Select * FROM board order by idx desc")
    List<BoardDTO> getAllUserInfo(RowBounds rowBounds);

    @Select("select count(*) from board")
    int getAllUserCount();

    @Select("SELECT count(*) FROM favorite WHERE user_idx = #{user_idx} AND board_idx = #{board_idx}")
    int isFavBoardExists(@Param("user_idx") int user_idx, @Param("board_idx") int board_idx);

    @Insert("insert into tjoeun.favorite(user_idx, board_idx) " +
          "values(#{user_idx}, #{board_idx})")
    int addFavBoard(FavoriteDTO favBoardDTO);

    @Delete("DELETE FROM favorite WHERE user_idx = #{user_idx} AND board_idx = #{board_idx}")
    void deleteFavBoard(@Param("user_idx") int user_idx, @Param("board_idx") int board_idx);

    @Select({"<script>"
    		+ "SELECT * from v_board_user "
    		+ "<where>"
    		+ "<if test='title != null'>"
    		+ "title like concat('%',#{title},'%')"
    		+ "</if>"
    		+ "<if test='username != null'>"
    		+ "username = #{username}"
    		+ "</if>"
    		+ "<if test='startdate != null'>"
    		+ "date between #{startdate} and #{enddate}"
    		+ "</if>"
    		+ "</where>"
    		+ "order by idx desc"
    		+ "</script>"})
    List<BoardDTO> getAdminBoardList(RowBounds rowBounds, Map<String, Object> paramMap);
    
    @Select({"<script>"
    		+ "SELECT count(*) from v_board_user "
    		+ "<where>"
    		+ "<if test='title != null'>"
    		+ "title like concat('%',#{title},'%')"
    		+ "</if>"
    		+ "<if test='username != null'>"
    		+ "username = #{username}"
    		+ "</if>"
    		+ "<if test='startdate != null'>"
    		+ "date between #{startdate} and #{enddate}"
    		+ "</if>"
    		+ "</where>"
    		+ "order by idx desc"
    		+ "</script>"})
    int getAdminBoardCount(Map<String, Object> paramMap);
    
    @Select({
      "<script>",
      "SELECT * FROM v_board_user",
      "<where>",
      "  <if test='title != null and title != \"\"'>",
      "    title LIKE CONCAT('%', #{title}, '%')",
      "  </if>",
      "  <if test='username != null and username != \"\"'>",
      "    username = #{username}",
      "  </if>",
      "  <if test='startdate != null and enddate != null'>",
      "    date BETWEEN #{startdate} AND #{enddate}",
      "  </if>",
      "  <if test='board_id != null'>",
      "    and board_id = #{board_id}",
      "  </if>",
      "</where>",
      "ORDER BY",
      "  <choose>",
      "    <when test='sort != null and order != null'> ${sort} ${order} </when>",
      "    <otherwise> idx DESC </otherwise>",
      "  </choose>",
      "</script>"
	  })
	  List<BoardDTO> getSortedBoard(RowBounds rowBounds, Map<String, Object> paramMap);

}

