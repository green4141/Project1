package com.tjoeun.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.dto.UserDTO;
import com.tjoeun.mapper.UserMapper;
//전체수정완료
@Repository
public class UserDAO {
	
	@Autowired
	private UserMapper userMapper;

	public String checkUserId(String id) {
		String username = userMapper.checkUserId(id);
		return username;
	}
	
	public void addUserInfo(UserDTO joinUserDTO) {
		userMapper.addUserInfo(joinUserDTO);
	}
	
	public UserDTO getLoginUserInfo(UserDTO loginProcUserDTO) {
		UserDTO loginProcUserDTO2 = userMapper.getLoginUserInfo(loginProcUserDTO);
		return loginProcUserDTO2;
	}
	
	public UserDTO getModifyUserInfo(int idx){
		UserDTO modifyUserDTO = userMapper.getModifyUserInfo(idx);
		return modifyUserDTO;		
    }
	
	public void modifyUserInfo(UserDTO modifyUserDTO) {
		userMapper.modifyUserInfo(modifyUserDTO);
	}
	
	
}
