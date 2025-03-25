package com.tjoeun.controller;


import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.UserService;
import com.tjoeun.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	
	@GetMapping("/login")
	public String login(@ModelAttribute("loginProcUserDTO") UserDTO useDTO,
			                @RequestParam(value="fail", defaultValue="false")	boolean fail,
			                Model model) {
		model.addAttribute("fail", fail);
		return "user/login";
	}
	
	@PostMapping("/loginProcedure")
	public String login(@Valid @ModelAttribute("loginProcUserDTO") UserDTO loginProcUserDTO, 
			                BindingResult result) {
		
		// 로그인 실패 - 유효성 검사 통과 못함
		if(result.hasErrors()) {
			return "user/login";
		}
		
		// 로그인 성공 - 유효성 검사 통과함
		
		// Service 에서
		// DB 에서 가져온 로그인한 회원의 정보를,
		// Session Scope 에 (Spring Framework)가 미리 생성해 놓은
		// UserDTO loginUserDTO 에 저장함
		userService.getLoginUserInfo(loginProcUserDTO);
		
		// Service 에서  loginUserDTO.setUserLogin(true); 를 작성하면
	  // 로그인에 성공하면 private boolean userLogin 가 true 가 됨
		if(loginUserDTO.isUserLogin() == true) {
			return "user/login_success";			
		}else {
			return "user/login_failure";
		}
		
		
	}
	
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserDTO") UserDTO joinUserDTO) {
		return "user/join";
	}
	
	@PostMapping("/join_procedure")
	public String join_procedure(@Valid @ModelAttribute("joinUserDTO") UserDTO joinUserDTO,
			                         BindingResult result) {
		// 유효성 검사 통과 못함
		if(result.hasErrors()) {
			return "user/join";
		}
		
		userService.addUserInfo(joinUserDTO);
		
		// 유효성 검사 통과함
		return "user/join_success";
	}
	
	@GetMapping("/modify")
	public String modify() {
		return "user/modify";
	}	
	@GetMapping("/logout")
	public String logout() {
	// userService.deleteUserLoginInfo() 에서 처리함
			// loginUserDTO.setUserLogin(false);		
			// Session Scope 에 있는 회원 정보 삭제하기
			userService.deleteUserLoginInfo();

		  // 로그아웃한 후 Session 에 있는 loginUserDTO 에 저장된
			// 회원 정보 확인하기
			System.out.println("loginUserDTO : " + loginUserDTO);

			return "user/logout";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
	
	
}
