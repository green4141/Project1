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
	
	public boolean checkUserId(String id) {
		String username = userDAO.checkUserId(id);

		if (username == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void addUserInfo(UserDTO joinUserDTO) {
		userDAO.addUserInfo(joinUserDTO);
	}
	

	public void getLoginUserInfo(UserDTO loginProcUserDTO) {
		UserDTO loginProcUserDTO2 = userDAO.getLoginUserInfo(loginProcUserDTO);
		

		if(loginProcUserDTO2 != null) {
			loginUserDTO.setIdx(loginProcUserDTO2.getIdx());
		  loginUserDTO.setUsername(loginProcUserDTO2.getUsername());
		  loginUserDTO.setName(loginProcUserDTO2.getName());
		  loginUserDTO.setId(loginProcUserDTO2.getId());
		  loginUserDTO.setPassword(loginProcUserDTO2.getPassword());
		  loginUserDTO.setRole(loginProcUserDTO2.getRole());
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
