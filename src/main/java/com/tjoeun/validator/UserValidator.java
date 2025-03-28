package com.tjoeun.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tjoeun.dto.UserDTO;
//전체수정완료
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
		}
		
	}
	

}
