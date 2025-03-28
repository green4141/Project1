package com.tjoeun.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
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
	


	public List<UserDTO> getAllUserList(RowBounds rowBounds) {
		return userMapper.getAllUserInfo(rowBounds);
	}

	public UserDTO getModifyUserInfo(int idx){
		UserDTO modifyUserDTO = userMapper.getModifyUserInfo(idx);
		return modifyUserDTO;		
    }
	
	public void modifyUserInfo(UserDTO modifyUserDTO) {
		userMapper.modifyUserInfo(modifyUserDTO);
	}
	
	public int getAllUserCount() {
		return userMapper.getAllUserCount();
	}

	public void userUpdate(UserDTO dto) {
		if(StringUtils.isBlank(dto.getName())) dto.setName(null);
		if(StringUtils.isBlank(dto.getPassword())) dto.setPassword(null);
		if(StringUtils.isBlank(dto.getUsername())) dto.setUsername(null);
		userMapper.updateUser(dto);
	}
	
	public void deleteUser(int idx) {
		userMapper.deleteUser(idx);
	}
}
