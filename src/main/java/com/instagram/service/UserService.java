package com.instagram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.instagram.dto.UserProfileDto;
import com.instagram.entity.User;
import com.instagram.handler.CustomApiException;
import com.instagram.handler.CustomException;
import com.instagram.handler.CustomValidationApiException;
import com.instagram.repository.SubscribeRepository;
import com.instagram.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final SubscribeRepository subscribeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional
	public User 회원프로필사진변경(int principalId, MultipartFile profileImageFile) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+profileImageFile.getOriginalFilename();
		System.out.println("이미지 파일이름 : "  + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		//예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath, profileImageFile.getBytes());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		User userEntity = userRepository.findById(principalId).orElseThrow(()->{
			return new CustomApiException("유저를 찾을 수 없습니다.");
		});
		userEntity.setProfileImageUrl(imageFileName);
				
		return userEntity;
	}
	
	@Transactional(readOnly = true)
	public UserProfileDto 회원프로필(int pageUserId, int principalId) {
		UserProfileDto dto = new UserProfileDto();
		
		//select  * from image where userId = :userId;
		User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
			return new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId == principalId);
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeStatus =  subscribeRepository.mSubscribeState(principalId, pageUserId);
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		
		dto.setSubscribeStatus(subscribeStatus == 1);
		dto.setSubscribeCount(subscribeCount);
		
		// 좋아요 카운트 추가하기
//		userEntity.getImages().forEach((image)->{
//			image.setLikeCount(image.getLikes().size());
//		});
				
		return dto;
	}

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