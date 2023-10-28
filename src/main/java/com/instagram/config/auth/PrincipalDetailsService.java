package com.instagram.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.instagram.entity.User;
import com.instagram.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//UserDetailsService를 상속받아 loadUserByUsername를 반드시 재정의해줘야한다
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) {
			return null;
		}else {
			return new PrincipalDetails(userEntity);
		}
	}

}
