package com.instagram.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Builder : builder 패턴으로 데이터를 담을 수 있게 해주는 어노테이션
 * @AllArgsConstructor : 모든 생성자를 자등으로 만들어주는
 * @NoArgsConstructor : 빈 생성자를 자동으로 만들어주는
 * @Data : 자동으로 Getter, Setter, toString을 만들어주는 
 * @Entity : DB에 테이블을 생성해주는 
 * 
 * */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(name="likes_uk", columnNames= {"imageId","userId"})		
})
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name ="imageId")
	@ManyToOne
	private Image image; // 좋아요가 어떤 이미지인지
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")
	@ManyToOne
	private User user; // 좋아요 누구 눌렀는지
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
