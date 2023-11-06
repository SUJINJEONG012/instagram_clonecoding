package com.instagram.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.instagram.dto.SubscribeDto;
import com.instagram.handler.CustomApiException;
import com.instagram.repository.SubscribeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	private final EntityManager em;
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId){
		
		// 쿼리 준비
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
		sb.append("if ((SELECT 1 FROM Subscribe WHERE fromUserId = 3 AND toUserId = u.id), 1, 0) subscribeState, ");
		sb.append("if ((3= u.id), 1, 0) equalUserState ");
		sb.append("FROM User u INNER JOIN Subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("WHERE s.fromUserId = 3");
		
		// 1.물음표 principalId
		// 2.물음표 principalId
		// 3.물음표 pageUserId
		
		// 쿼리 완성
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, principalId);
				
		
		// 쿼리 실행 (qlrm 라이브러리 필요 = DTO에 DB결과를 매핑하기 위해서)
		JpaResultMapper result = new JpaResultMapper();
		
		List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
		
		return subscribeDtos;
	}
	
	
//	@Transactional
//	public void 구독하기(int fromUserId, int toUserId) {
//		try {
//			subscribeRepository.mSubscribe(fromUserId, toUserId);
//		}catch(Exception e) {
//			throw new CustomApiException("이미 구독을 하였습니다.");
//		}	
//	}
//	
//	@Transactional
//	public void 구독취소하기(int fromUserId, int toUserId) {
//		subscribeRepository.mSubscribe(fromUserId, toUserId);
//	}
	
	
}
