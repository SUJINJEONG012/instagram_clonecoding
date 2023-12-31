package com.instagram.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.config.auth.PrincipalDetails;
import com.instagram.dto.CMRespDto;
import com.instagram.entity.Image;
import com.instagram.service.ImageService;
import com.instagram.service.LikesService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

	private final ImageService imageService;
	private final LikesService likesService;
	
	
	@GetMapping("/api/image")
	public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails, @PageableDefault(size=3) Pageable pageable){
		Page<Image> images = imageService.이미지스토리(principalDetails.getUser().getId(), pageable);
		return new ResponseEntity<>(new CMRespDto<>(1,"성공dd", images), HttpStatus.OK);
	}
	
	@PostMapping("/api/image/{imageId}/likes")
	public ResponseEntity<?> likes(@PathVariable int imageId, @AuthenticationPrincipal PrincipalDetails principalDetails){
		likesService.좋아요(imageId, principalDetails.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "좋아요성공", null), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/api/image/{imageId}/likes")
	public ResponseEntity<?> unLikes(@PathVariable int imageId, @AuthenticationPrincipal PrincipalDetails principalDetails){
		likesService.좋아요취소(imageId, principalDetails.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "좋아요취소성공", null), HttpStatus.OK);
	}
	

}
