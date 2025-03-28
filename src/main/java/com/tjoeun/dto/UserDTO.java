package com.tjoeun.dto;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
	private int idx;
	
	@Size(min=2, max=4)
	@Pattern(regexp="[가-힣]*")
	private String name; // 실명
	
	@Size(min=4, max=20)
	@Pattern(regexp="[A-Za-z0-9]*")
	private String id; // 아이디
	
	@Size(min=4, max=20)
	@Pattern(regexp="[A-Za-z0-9]*")
	private String password; // 비밀번호
	
	@Size(min=4, max=20)
	@Pattern(regexp="[A-Za-z0-9]*")
	private String password2; // 비밀번호 확인
	
	@Size(min=2, max=10)
	@Pattern(regexp="^[가-힣A-Za-z0-9]+$")
	private String username; // 닉네임
	
	@Size(min=2, max=10)
	@Pattern(regexp="^[가-힣A-Za-z0-9]+$")
	private String username2; // 닉네임수정

  private int role;// 0: 학생, 1: 선생
	
	// 회원 가입 여부 저장
	private boolean userIdExist = false;
	
	// 로그인 상태 정보 저장
	private boolean userLogin = false;
	
}


