package com.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
