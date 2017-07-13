package com.example.demo.security;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class FooCookieTokenFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getCookies() != null) {
			Map<String, Cookie> cookieMap = Stream.of(request.getCookies())
					.collect(Collectors.toMap(Cookie::getName, Function.identity()));
			if (cookieMap.containsKey("uid")) {
				String uid = cookieMap.get("uid").getValue();
				FooCookieToken cookieToken = new FooCookieToken(uid);
				SecurityContextHolder.getContext().setAuthentication(cookieToken);
			}
		}
		filterChain.doFilter(request, response);

	}
}
