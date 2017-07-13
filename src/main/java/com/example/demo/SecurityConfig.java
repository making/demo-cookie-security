package com.example.demo;

import com.example.demo.security.FooAuthenticationProvider;
import com.example.demo.security.FooCookieTokenFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
				.addFilterBefore(new FooCookieTokenFilter(),
						BasicAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(new FooAuthenticationProvider());
	}
}
