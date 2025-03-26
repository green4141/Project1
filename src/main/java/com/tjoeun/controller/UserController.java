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
		userService.getLoginUserInfo(loginProcUserDTO);

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
		if(result.hasErrors()) {
			return "user/join";
		}
		userService.addUserInfo(joinUserDTO);
		return "user/join_success";
	}
	// 회원정보 확인
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserDTO") UserDTO modifyUserDTO) {
		userService.getModifyUserInfo(modifyUserDTO);
		return "user/modify";
	}
	
	// 비밀번호 변경
	@PostMapping("/modifyProcedure")
	public String modifyProcedure(@Valid @ModelAttribute("modifyUserDTO") UserDTO modifyUserDTO,
		                          BindingResult result){
		if(result.hasErrors()){
			return "user/modify";			
	  }
		userService.modifyUserInfo(modifyUserDTO);
		return "user/modify_success";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout() {
			userService.deleteUserLoginInfo();
			System.out.println("loginUserDTO : " + loginUserDTO);

			return "user/logout";
	}
	
	// 접근제한
	@GetMapping("/cannot_login")
	public String cannot_login(){
		return "user/cannot_login";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
	
}
