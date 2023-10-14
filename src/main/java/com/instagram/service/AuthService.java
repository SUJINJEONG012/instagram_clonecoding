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

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	/*
	 * Errorss는 반드시 @Valid를 선언한Request객체 바로 뒤에 와야한다.
	 * 
	 * 
	 * */
	@Transactional
	public Map<String, String> validHanding(Errors errors){
		Map<String, String> validResult = new HashMap<>();
		for(FieldError error : errors.getFieldErrors()) {
			validResult.put("valid_"+error.getField(), error.getDefaultMessage());
		}
		return validResult;		
	}
	
	
	// 유효성검사로는 중복된값을 확인할 수 없기 때문에 dto로 username을 뽑아 db에 해당아이디가 있는지 확인
	@Transactional
	public boolean findUser(String username) {
		return userRepository.existsByUsername(username);
	}
	
	public void userSignup(SignUpDto signupDto) {
		String encPassword = bCryptPasswordEncoder.encode(signupDto.getPassword());
		signupDto.setPassword(encPassword);
		User user = signupDto.toEntity();
		userRepository.save(user);
	}
	
	
	
}
