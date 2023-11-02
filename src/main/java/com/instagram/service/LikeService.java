package com.instagram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.instagram.repository.LikesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeService {

	private final LikesRepository likesRepository;
	
	@Transactional
	public void 좋아요(int imageId, int principalId) {
		likesRepository.mLikes(imageId, principalId);
	}
	
	@Transactional
	public void 좋아요취소(int imageId, int principalId) {
		likesRepository.mUnLikes(imageId, principalId);
	}
}