package com.tjoeun.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.tjoeun.dto.UserDTO;
//전체수정완료
public interface UserMapper {
	 
	// 회원 가입 여부 조회하기
	@Select("SELECT username " +
			    "FROM user " +
			    "WHERE id = #{id}")
	String checkUserId(String id);

	// 회원 가입하기
	@Insert("INSERT INTO tjoeun.user(name, id, password, username, role) VALUES(#{name}, #{id}, #{password}, #{username}, #{role})")
	void addUserInfo(UserDTO joinUserDTO);
	
	@Select("SELECT * " +
	        "FROM user " +
	        "WHERE id = #{id} AND password = #{password}")
	UserDTO getLoginUserInfo(UserDTO loginProcUserDTO);

	
}