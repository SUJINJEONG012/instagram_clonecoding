package com.instagram.repository;

import java.awt.Image;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	@Query(value =  "select from image where userId in (select toUserId from subscribe where fromUserId = :principalId) order by id desc", nativeQuery = true)
	Page<Image> mStory(@Param("principalId") int principalId, Pageable pageable);

	@Query(value = "SELECT i.* FROM image i INNER JOIN (SELECT imageId, COUNT(imageId) likeCount FROM likes GROUP BY imageId) c ON i.id = c.imageId ORDER BY likeCount DESC", nativeQuery = true)
	List<Image> mPopular();
	
}