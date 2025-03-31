package com.tjoeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tjoeun.dto.ReplyDTO;

public interface ReplyMapper {
	@Insert("insert into reply(user_idx, board_idx, content) values ("
			+ "#{user_idx}, #{board_idx}, #{content})")
	@Options(useGeneratedKeys = true, keyProperty = "idx")
	public int addReply(ReplyDTO replyDTO);
	
	@Select("select * from v_reply_user where board_idx = #{board_id}")
	public List<ReplyDTO> getReplyList(int board_id);
	
	@Update("update reply set content = #{content} where idx = #{idx}")
	public void updateReply(ReplyDTO replyDTO);
	
	@Delete("delete from reply where idx = #{idx}")
	public void deleteReply(int idx);
	
}
