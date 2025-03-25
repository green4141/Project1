package com.tjoeun.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.dto.UserDTO;
//전체수정완료
@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	
	// Service 에서
	// 사용자가 회원가입하면서 입력한 아이디로
	// DB 에서 가져온 이름이 조회되는지 비교하기
	public boolean checkUserId(String id) {
		String username = userDAO.checkUserId(id);
		// 사용자가 입력한 아이디로 가입한 회원이 없음
		// else <-- 이미 존재하는 아이디입니다
		if (username == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void addUserInfo(UserDTO joinUserDTO) {
		userDAO.addUserInfo(joinUserDTO);
	}
	
	// Service 에서
	// DB 에서 가져온 로그인한 회원의 정보를,
	// Session Scope 에 (Spring Framework)가 미리 생성해 놓은
	// UserDTO loginUserDTO 에 저장함
	public void getLoginUserInfo(UserDTO loginProcUserDTO) {
		UserDTO loginProcUserDTO2 = userDAO.getLoginUserInfo(loginProcUserDTO);
		
		// loginUserDTO <-- Session Scope 에 생성된 UserDTO
		// DB 로부터 로그인한 회원의 정보를 정상적으로 가져온 경우
		if(loginProcUserDTO2 != null) {
		  loginUserDTO.setIdx(loginProcUserDTO2.getIdx());
		  loginUserDTO.setUsername(loginProcUserDTO2.getUsername());
		  // loginUserDTO.setUser_id(loginProcUserDTO2.getUser_id());
		  // loginUserDTO.setUser_pw(loginProcUserDTO2.getUser_pw());
		  loginUserDTO.setUserLogin(true);
		}
		
		System.out.println("loginUserDTO : " + loginUserDTO);
		
		
		return;
	}
	
//로그아웃할 때 loginUserDTOloginUserDTO 의 userLogin 은 false 로 하고
	// 로그인했전 회원정보 초기화하기
	public void deleteUserLoginInfo(){
		loginUserDTO.setUserLogin(false);
		loginUserDTO.setIdx(0);
		loginUserDTO.setName("");
		loginUserDTO.setUsername("");
		loginUserDTO.setId("");
		loginUserDTO.setPassword("");
		loginUserDTO.setRole(-1);
	}
   
}
