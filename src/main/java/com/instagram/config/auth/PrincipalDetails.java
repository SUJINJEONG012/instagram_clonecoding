package com.instagram.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.instagram.entity.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails  {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Map<String, Object> attributes;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
	}
	
	//계정이 가지고 있는 권한을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		Collection<GrantedAuthority> collector = new ArrayList<>();
		collector.add(()->{ return user.getRole();});
		return collector;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
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
	
	public Map<String, Object> getAttributes() {
		return attributes;  // {id:343434343, name: , email:ssarmango@nate.com}
	}

	
	public String getName() {
		// TODO Auto-generated method stub
		return (String) attributes.get("name");
	}
	
	
	

	
}
