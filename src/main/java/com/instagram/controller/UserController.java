package com.instagram.controller;

import org.springframework.stereotype.Controller;

import com.instagram.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	
	private final UserService userService;
	

}
