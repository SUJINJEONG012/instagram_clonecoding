package com.instagram.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.instagram.dto.SignUpDto;
import com.instagram.entity.User;
import com.instagram.service.AuthService;
import com.instagram.util.Script;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI 할때 사용
@Controller
public class AuthController {
	
	
	private final AuthService authService; 

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	
	
	//로그인실패시
	@GetMapping("/auth/failed")
	public String failedSignin(Model model) {
		return Script.locationMsg("/auth/signin", "아이디 또는 비밀번호를 잘못 입력하셨습니다.", model);
	}
	
	/*
	 * Errors는 반드시 Request객체 바로뒤에 위치해야함
	 * errors.hasErrors()는 유효성검사에 실패했을시 true값을 반환한다
	 * */
	@PostMapping("/auth/signup")
		public String signup(@Valid SignUpDto signupDto, BindingResult bindingResult) {
			 User user = signupDto.toEntity();
			 authService.회원가입(user);
			 System.out.println( "ddddddddddddd" + user);
			 return "auth/signin";
					
		}
}
