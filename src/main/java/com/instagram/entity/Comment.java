package com.instagram.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {

	private int id;
	private String content;
	private User user;
	private Image image;
	private LocalDateTime createDate;
	
	@PrePersist
	private void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
