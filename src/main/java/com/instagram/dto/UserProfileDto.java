package com.instagram.dto;

import com.instagram.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {

	private boolean pageOwnerState;
	private int imageCount;
	private boolean subscribeStatus;
	private int subscribeCount;
	private User user;
}
