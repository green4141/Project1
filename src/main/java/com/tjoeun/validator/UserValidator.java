package com.tjoeun.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tjoeun.dto.UserDTO;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDTO userDTO = (UserDTO)target;
		
		String beanName = errors.getObjectName();
		
		if(beanName.equals("joinUserDTO")) {
			if(userDTO.getPassword().equals(userDTO.getPassword2()) == false) {
				errors.rejectValue("password", "NotEquals");
				errors.rejectValue("password2", "NotEquals");
			}
			// 중복확인 버튼 누르지 않고 회원가입 버튼을 눌렀을 때 
			if (userDTO.isUserIdExist() == false) {
				errors.rejectValue("id", "DidntCheckUserId");
			}
			if (!userDTO.isUserNameExist() ) {
				errors.rejectValue("username", "DidntCheckUserName");
			}
		}
		
		if (beanName.equals("modifyUserDTO")) {
      if (userDTO.getPassword() == null || userDTO.getPassword2() == null ||
          !userDTO.getPassword().equals(userDTO.getPassword2())) {
          errors.rejectValue("password", "NotEquals.modifyUserDTO.password");
          errors.rejectValue("password2", "NotEquals.modifyUserDTO.password2");
      }

      if (!userDTO.isUserName2Exist()) {
          errors.rejectValue("username2", "DidntCheckUserName");
      }
  }
	}
}
