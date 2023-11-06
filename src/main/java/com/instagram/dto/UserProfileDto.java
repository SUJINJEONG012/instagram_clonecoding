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
	private boolean pageOwnerState; // 페이지 주인여부  1:주인, -1주인x
	private int imageCount; // 업로드 된 페이지 개수
	private boolean subscribeStatus; // 구독상태, 했으면 true, 안했으면 false
	private int subscribeCount; //구독자 수 카운팅
	private User user; // 접속한 유저정보를 받을 유저 오브젝트
}
