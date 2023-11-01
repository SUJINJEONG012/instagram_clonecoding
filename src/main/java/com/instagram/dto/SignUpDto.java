package com.instagram.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.instagram.entity.User;

import lombok.Data;

@Data
public class SignUpDto {
	
	@NotBlank(message = "아이디를 입력해주세요")
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{5,30}$", message = "아이디는 특수문자를 제외한<br>5자이상이여야 합니다")
	private String username;
	
	@NotBlank(message="비밀번호를 입력해주세요")
	//@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}\", message = \"최소 하나의 문자 및 숫자를 포함한<br>8~16자이여야 합니다")
	private String password;
	
	@NotBlank
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z]{2,30}$", message = "숫자 또는 특수문자를 제외한<br>2자이상 입력해주세요")
	private String name;
	
	@NotBlank(message = "이메일을 입력해주세요")
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다") 
	private String email;
	
	
	/*
	 * DB에 데이터를 저장해야하는데 이를 위해 DTO를 Entity로 변환해줘야 한다.
	 * 사용자로부터 입력받지 않은 값이 하나 있는데 ROLE(권한)값을 USER로 지정
	 * */
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
	
}
