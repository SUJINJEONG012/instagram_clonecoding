package com.instagram.dto;

import javax.validation.constraints.NotBlank;

import com.instagram.entity.User;

import lombok.Data;

@Data
public class UserUpdateDto {
	
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	//조금 위험함. 코드수정이 필요한 
	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password)
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
				
	}
}
