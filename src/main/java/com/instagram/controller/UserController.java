package com.instagram.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.instagram.config.auth.PrincipalDetails;
import com.instagram.dto.UserProfileDto;
import com.instagram.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	//프로필로 이동 , 세션정보를 들고 있어야 한다.
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
	
		UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
		model.addAttribute("dto", dto);
		//System.out.println("@@@@@ 회원프로필 : " + principalDetails.getUser());		
		return "user/profile"; 
	}
	
	//회원수정 페이지로 이동 -> 세션정보를 들고 있어야 한다.
	@GetMapping("/user/{id}/update")
	public String updateForm(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		//System.out.println("@@@@@ 세션정보 : " + principalDetails.getUser());
		return "user/update";
	}
}
