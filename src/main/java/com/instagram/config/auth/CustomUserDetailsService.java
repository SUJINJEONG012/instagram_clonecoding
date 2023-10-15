package com.instagram.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.instagram.entity.User;
import com.instagram.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//UserDetailsService를 상속받아 loadUserByUsername를 반드시 재정의해줘야한다
		User user = userRepository.findByUsername(username);
		
		if(user == null) throw new UsernameNotFoundException(username);
		
		CustomUserDetails customUser = new CustomUserDetails();
		customUser.setUsername(user.getUsername());
		customUser.setPassword(user.getPassword());
		customUser.setRole(user.getRole());
		return customUser;
	}

}
