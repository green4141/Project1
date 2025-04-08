package com.tjoeun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import org.apache.ibatis.annotations.Update;


import com.tjoeun.dto.UserDTO;

public interface UserMapper {
	 
	// 회원 가입 여부 조회하기
	@Select("SELECT username " +
			    "FROM user " +
			    "WHERE id = #{id}")
	String checkUserId(String id);
	@Select("SELECT username " +
		    "FROM user " +
		    "WHERE username = #{username}")
	String checkUserName(String username);
	

	// 회원 가입하기
	@Insert("INSERT INTO tjoeun.user(name, id, password, username, role) VALUES(#{name}, #{id}, #{password}, #{username}, #{role})")
	void addUserInfo(UserDTO joinUserDTO);
	
	@Select("SELECT * " +
	        "FROM user " +
	        "WHERE id = #{id} AND password = #{password}")
	UserDTO getLoginUserInfo(UserDTO loginProcUserDTO);


	// 전체 회원 목록 가져오기
	@Select("SELECT * FROM user")
	List<UserDTO> getAllUserInfo(RowBounds rowBounds);

	// 회원 검색 목록 가져오기
	@Select({"<script>"
				+ "select * from user"
				+ "<where>"
					+ "<if test='id != null'>"
						+ "id = #{id}"
					+ "</if>"
					+ "<if test='name != null'>"
						+ "name = #{name}"
					+ "</if>"
					+ "<if test='username != null'>"
						+ "username = #{username}"
					+ "</if>"
					+ "<if test='role != null'>"
						+ "role = #{role}"
					+ "</if>"
				+ "</where>"
				+ "order by idx desc"
			+ "</script>"})
	List<UserDTO> userSearch(RowBounds rowbounds, Map<String, Object> searchParam);
	
	// 회원정보 조회
	@Select("SELECT * FROM user WHERE idx = #{idx}")
	UserDTO getModifyUserInfo(int idx);
	
	//패스워드 및 닉네임 업데이트
	@Update("UPDATE user SET password = #{password}, username = #{username2} WHERE idx = #{idx}")
	void modifyUserInfo(UserDTO modifyUserDTO);
	
	// 전체 회원 수 구하기
	@Select("SELECT count(*) FROM user")
	int getAllUserCount();
	
	// 유저 검색 수 구하기
	@Select({"<script>"
				+ "select count(*) from user"
				+ "<where>"
					+ "<if test='id != null'>"
						+ "id = #{id}"
					+ "</if>"
					+ "<if test='name != null'>"
						+ "name = #{name}"
					+ "</if>"
					+ "<if test='username != null'>"
						+ "username = #{username}"
					+ "</if>"
					+ "<if test='role != null'>"
						+ "role = #{role}"
					+ "</if>"
				+ "</where>"
				+ "order by idx desc"
			+ "</script>"})
	int userSearchCount(Map<String, Object> searchParam);
	
	// 유저 정보 변경(어드민)
	@Update({"<script>"
				+ "UPDATE user "
				+ "<set>"
					+ "role = #{role} "
					+ "<if test = 'username != null'>"
						+ ", username = #{username} "
					+ "</if>"
					+ "<if test='name != null'>"
						+ ", name = #{name} "
					+ "</if>"
					+ "<if test='password != null'>"
						+ ", password = #{password} "
					+ "</if>"
				+ "</set>"
				+ "where idx = #{idx}"
			+ "</script>"})
	void updateUser(UserDTO dto);
	
	// 회원 삭제(어드민)
	@Delete("delete from user where idx = #{idx}")
	void deleteUser(int idx);
	 
	@Select({
	  "<script>",
	  "SELECT * FROM user",
	  "<where>",
	  "  <if test='id != null and id != \"\"'> id LIKE CONCAT('%', #{id}, '%') </if>",
	  "  <if test='name != null and name != \"\"'> name LIKE CONCAT('%', #{name}, '%') </if>",
	  "  <if test='username != null and username != \"\"'> username LIKE CONCAT('%', #{username}, '%') </if>",
	  "  <if test='role != null'> role = #{role} </if>",
	  "</where>",
	  "ORDER BY",
	  "    <if test='sort != null and order != null'> ${sort} ${order}, </if>",
	  "    idx DESC",
	  "</script>"
	})
	List<UserDTO> getSortedUser(RowBounds rowBounds, Map<String, Object> paramMap);
}
