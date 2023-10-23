package com.instagram.dto;

import org.springframework.web.multipart.MultipartFile;

import com.instagram.entity.Image;
import com.instagram.entity.User;

import lombok.Data;

@Data
public class ImageUploadDto {

	private MultipartFile file;
	private String caption;
	
	public Image toEntity(String postImageUrl, User user) {
		return Image.builder()
				.caption(caption)
				.postImageUrl(postImageUrl)
				.user(user)
				.build();
	}
}
