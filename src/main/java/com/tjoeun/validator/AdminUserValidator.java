package com.tjoeun.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tjoeun.dto.UserDTO;

@Component
public class AdminUserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;

        if ((userDTO.getPassword() != null && !userDTO.getPassword().trim().isEmpty()) || (userDTO.getPassword2() != null && !userDTO.getPassword2().trim().isEmpty())) {
        	if (userDTO.getPassword().length() < 4 || userDTO.getPassword().length() > 20) {
            errors.rejectValue("password", "Size.adminUserDTO.password");
          }
  
          if (userDTO.getPassword2().length() < 4 || userDTO.getPassword2().length() > 20) {
              errors.rejectValue("password2", "Size.adminUserDTO.password2");
          }
          if (userDTO.getPassword() == null || userDTO.getPassword2() == null || !userDTO.getPassword().equals(userDTO.getPassword2())) {
              errors.rejectValue("password", "NotEquals.adminUserDTO.password");
              errors.rejectValue("password2", "NotEquals.adminUserDTO.password2");
          }
      } 
    }
}

