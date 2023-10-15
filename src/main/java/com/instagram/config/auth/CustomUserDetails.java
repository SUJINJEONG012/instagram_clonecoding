package com.instagram.config.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails  {
	
	private String username;
	private String password;
	private String role;
	
	//계정이 가지고 있는 권한을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		return Collections.singletonList(new SimpleGrantedAuthority(this.role));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	//계정이 만료되었는지를 리턴 : true : 만료되지 않음
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있는지를 리턴
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//패스워드가 만료되었는지를 리턴
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정이 사용가능한지를 리턴
	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
