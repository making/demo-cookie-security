package com.example.demo.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class FooAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String uid = (String) authentication.getPrincipal();
		// no check!! Token should be decoded here.
		
		UserDetails user = new User(uid, "",
				AuthorityUtils.createAuthorityList("ROLE_USER"));
		return new FooAuthentication(user);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return FooCookieToken.class.equals(authentication);
	}
}
