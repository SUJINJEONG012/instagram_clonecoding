package com.instagram.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class User {

	//DB넘버링 전략
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(length =50, unique = true, nullable =false)
	private String username;
	
	// null값 허용안함
	@Column(nullable =false)
	/* 
	 * 클라이언트와 서버간의 통신을 통해 user개체가 전달되면 패스워닥 노출되는 문제가 생김.
	 * @Jsonlgnorre 어노테이션을 붙이면 user개체가 전달될때 패스워드 필드를 제외하기 때문에 문제를 해결 할 수 있음
	 * 하지만 validation 체크를 할때 해당 필드값을 읽을 수 없으므로 
	 * @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)를 붙이면 요청시에는
	 * 해당 필드값을 제외하여 전달해주고 validation 체크도 가능하다
	*/
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	private String email;
	private String phone;
	private String gender;
	
	// 회원의 프로필사진이 저장된 서버내부의 주소값을 저장
	private String profileImage;
	
	//모든 회원의 권한값을user로 설정
	@ColumnDefault("'USER'")
	private String role;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
