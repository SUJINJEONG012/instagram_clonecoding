package com.instagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.instagram.config.auth.CustomUserDetails;
import com.instagram.service.AuthService;

@Controller
public class UserController {

	@Autowired
	private AuthService authService;
	
	@GetMapping("/user/update/{id}")
	public String updateForm(@PathVariable int id, @AuthenticationPrincipal CustomUserDetails customUser, Model model) {
		
	}
}
