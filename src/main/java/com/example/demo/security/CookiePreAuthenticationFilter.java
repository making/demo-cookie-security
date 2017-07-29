package com.example.demo.security;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class CookiePreAuthenticationFilter
		extends AbstractPreAuthenticatedProcessingFilter {

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		if (request.getCookies() != null) {
			Map<String, Cookie> cookieMap = Stream.of(request.getCookies())
					.collect(Collectors.toMap(Cookie::getName, Function.identity()));
			if (cookieMap.containsKey("uid")) {
				String uid = cookieMap.get("uid").getValue();
				return uid;
			}
		}
		return null;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return "N/A";
	}
}
