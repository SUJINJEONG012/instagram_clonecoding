package com.instagram.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.instagram.dto.SignUpDto;
import com.instagram.entity.User;
import com.instagram.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final  UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Transactional
	public User 회원가입(User user) throws RuntimeException {
		//회원가입 진행
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");
		User userEntity = null;
		userEntity = userRepository.save(user);
		
		return userEntity;
	}
	
	
}
