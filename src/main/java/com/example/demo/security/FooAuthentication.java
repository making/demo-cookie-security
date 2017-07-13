package com.example.demo.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class FooAuthentication implements Authentication {
	private final UserDetails userDetails;

	public FooAuthentication(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.userDetails.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.userDetails;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {

	}

	@Override
	public String getName() {
		return this.userDetails.getUsername();
	}
}
