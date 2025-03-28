package com.tjoeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import org.apache.ibatis.session.RowBounds;

import org.apache.ibatis.annotations.Update;


import com.tjoeun.dto.UserDTO;

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


	// 전체 회원 목록 가져오기
	@Select("SELECT * FROM user")
	List<UserDTO> getAllUserInfo(RowBounds rowBounds);

	// 회원정보 조회
	@Select("SELECT * FROM user WHERE idx = #{idx}")
	UserDTO getModifyUserInfo(int idx);
	
	//패스워드 및 닉네임 업데이트
	@Update("UPDATE user SET password = #{password}, username = #{username2} WHERE idx = #{idx}")
	void modifyUserInfo(UserDTO modifyUserDTO);
	
	// 전체 회원 수 구하기
	@Select("SELECT count(*) FROM user")
	int getAllUserCount();
	

}