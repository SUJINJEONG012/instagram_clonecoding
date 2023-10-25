package com.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.instagram.entity.Likes;

public interface LikesRepository extends JpaRepository<Likes, Integer>{

	@Modifying
	@Query(value= "insert into likes(imageId, userId, createDate) values(:imageId, :principalId, now())", nativeQuery = true)
	int mLikes(@Param("imageId") int imageId, @Param("principalId") int principalId);
	
	@Modifying
	@Query(value = "delete from likes where imageId = :imageId AND userId = :principalId", nativeQuery = true)
	int mUnLikes(@Param("imageId") int imageId, @Param("principalId") int principalId);

}
