package com.instagram.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.instagram.entity.User;
import com.instagram.handler.CustomValidationApiException;
import com.instagram.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Value("${file.path}")
	private String uploadFolder;

	@Transactional
	public User 회원수정(int id, User user) {
		//1. 영속화
		//1. 무조건 찾았다. 걱정마 get() 2. 못찾았어 익센션 발동시킬께
		User userEntity = userRepository.findById(id).orElseThrow(()->{return new CustomValidationApiException("찾을 수 없는 id 입니다.");});
		// 2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)
				userEntity.setName(user.getName());
				
				String rawPassword = user.getPassword();
				String encPassword = bCryptPasswordEncoder.encode(rawPassword);
				
				userEntity.setPassword(encPassword);
				userEntity.setBio(user.getBio());
				userEntity.setWebsite(user.getWebsite());
				userEntity.setPhone(user.getPhone());
				userEntity.setGender(user.getGender());
				return userEntity;
		}
}