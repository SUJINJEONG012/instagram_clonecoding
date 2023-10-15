package com.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	//유저아이디 중복될수 없다. 유효성검사로는 중복된 값을 확인할 수 없기 때문에 dto에서username을 뽑기위해 추가한다.
	boolean existsByUsername(String username);
	
	User findByUsername(String usersname);
}
