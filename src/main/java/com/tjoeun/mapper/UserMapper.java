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
	//                                (1, '더조은', 'tjoeun', '1234')
	//@Insert("INSERT INTO user_table VALUES(user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	@Insert("INSERT INTO tjoeun.user(name, id, password, username, role) VALUES(#{name}, #{id}, #{password}, #{username}, #{role})")
	//insert into tjoeun.user(name, id, password, username, role) 
	//values(‘홍길동’, ‘honggildong’, ‘hong1234’, ‘hong’, 0);
	void addUserInfo(UserDTO joinUserDTO);
	
	// 로그인 성공했을 때, 회원 정보 가져오기
	//  ㄴ 가져와서, Spring Framework 가  Session Scope 에
	//     서버 실행할 때 자동으로 생성해 놓은 UserDTO 에 
	//     로그인한 회원의 정보를 위함 
	@Select("SELECT * " +
	        "FROM user " +
	        "WHERE id = #{id} AND password = ${password}")
	UserDTO getLoginUserInfo(UserDTO loginProcUserDTO);

	
}



