package com.tjoeun.dao;

import java.util.List;
import java.util.Map;

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
	public String checkUserName(String username) {
		String serverusername = userMapper.checkUserName(username);
		return serverusername;
	}
	public void addUserInfo(UserDTO joinUserDTO) {
		userMapper.addUserInfo(joinUserDTO);
	}
	
	public UserDTO getLoginUserInfo(UserDTO loginProcUserDTO) {
		UserDTO loginProcUserDTO2 = userMapper.getLoginUserInfo(loginProcUserDTO);
		return loginProcUserDTO2;
	}
	
	public List<UserDTO> getUserList(RowBounds rowBounds, Map<String, Object> searchParam) {
		if(searchParam.isEmpty()) return userMapper.getAllUserInfo(rowBounds);
		else return userMapper.userSearch(rowBounds, searchParam);
	}

	public UserDTO getModifyUserInfo(int idx){
		UserDTO modifyUserDTO = userMapper.getModifyUserInfo(idx);
		return modifyUserDTO;		
    }
	
	public void modifyUserInfo(UserDTO modifyUserDTO) {
		userMapper.modifyUserInfo(modifyUserDTO);
	}
	
	public int getUserCount(Map<String, Object> searchParameter) {
		if(searchParameter.isEmpty()) return userMapper.getAllUserCount();
		else return userMapper.userSearchCount(searchParameter);
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
