package com.instagram.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

	//DB넘버링 전략
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(length =100, unique = true)
	private String username;
	
	// null값 허용안함
	/* 
	 * 클라이언트와 서버간의 통신을 통해 user개체가 전달되면 패스워닥 노출되는 문제가 생김.
	 * @Jsonlgnorre 어노테이션을 붙이면 user개체가 전달될때 패스워드 필드를 제외하기 때문에 문제를 해결 할 수 있음
	 * 하지만 validation 체크를 할때 해당 필드값을 읽을 수 없으므로 
	 * @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)를 붙이면 요청시에는
	 * 해당 필드값을 제외하여 전달해주고 validation 체크도 가능하다
	*/
	@Column(nullable =false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	private String website;
	private String bio; //자기소개
	
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	
	// 회원의 프로필사진이 저장된 서버내부의 주소값을 저장
	private String profileImageUrl;
	//모든 회원의 권한값을user로 설정, dto에 권한값을 줘서 entity에서는 주석처리
	//@ColumnDefault("'USER'")
	private String role;
	
	/* 나는 연관관계의 주인이 아니다. 그러므로 테이블에 칼럼을 만들지마
	 * User를 select할 때 해당 user id로 등록된 image들을 다 가져와.
	 * Lazy = 
	 * Eager =
	 * .*/
	@OneToMany(mappedBy ="user", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"user"})
	private List<Image> images; //양방향 매핑
	

	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", website="
				+ website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender=" + gender
				+ ", profileImageUrl=" + profileImageUrl + ", role=" + role +", createDate="
				+ createDate + "]";
	}
	
	
}
