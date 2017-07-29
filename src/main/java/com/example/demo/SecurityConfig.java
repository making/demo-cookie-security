package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.example.demo.security.CookieAuthenticationUserDetailsService;
import com.example.demo.security.CookiePreAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.anyRequest().authenticated() //
				.and() //
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
				.and() //
				.addFilterAt(preAuthenticatedFilter(),
						AbstractPreAuthenticatedProcessingFilter.class);
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
		authenticationProvider.setPreAuthenticatedUserDetailsService(
				authenticationUserDetailsService());
		return authenticationProvider;
	}

	@Bean
	AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService() {
		return new CookieAuthenticationUserDetailsService();
	}

	@Bean
	CookiePreAuthenticationFilter preAuthenticatedFilter() throws Exception {
		CookiePreAuthenticationFilter preAuthenticatedFilter = new CookiePreAuthenticationFilter();
		preAuthenticatedFilter.setAuthenticationManager(authenticationManager());
		return preAuthenticatedFilter;
	}
}
