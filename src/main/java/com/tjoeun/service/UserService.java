package com.tjoeun.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.dto.UserDTO;

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
		
		//System.out.println("loginUserDTO : " + loginUserDTO);
		
		
		return;
	}
	
	//로그인 정보 초기화
	public void deleteUserLoginInfo(){
		loginUserDTO.setUserLogin(false);
		loginUserDTO.setIdx(0);
		loginUserDTO.setName("");
		loginUserDTO.setUsername("");
		loginUserDTO.setId("");
		loginUserDTO.setPassword("");
		loginUserDTO.setRole(-1);
	}
	
	//회원정보값
	public void getModifyUserInfo(UserDTO modifyUserDTO) {
		UserDTO tmpModifyUserDTO = userDAO.getModifyUserInfo(loginUserDTO.getIdx());
		
		modifyUserDTO.setIdx(tmpModifyUserDTO.getIdx());
		modifyUserDTO.setName(tmpModifyUserDTO.getName());
		modifyUserDTO.setUsername(tmpModifyUserDTO.getUsername());
		modifyUserDTO.setId(tmpModifyUserDTO.getId());
		modifyUserDTO.setRole(tmpModifyUserDTO.getRole());
	}
	
	//패스워드 변경
	public void modifyUserInfo(UserDTO modifyUserDTO) {
		modifyUserDTO.setIdx(loginUserDTO.getIdx());
		userDAO.modifyUserInfo(modifyUserDTO);
	}
	
   
}
