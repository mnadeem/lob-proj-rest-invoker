package com.org.lob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.org.lob.support.security.jwt.BearerJWTTokenAuthInterceptor;
import com.org.lob.support.security.jwt.DefaultJwtTokenProvider;
import com.org.lob.support.security.jwt.DefaultJwtTokenService;
import com.org.lob.support.security.jwt.JwtTokenProvider;
import com.org.lob.support.security.jwt.JwtTokenService;

@Configuration
public class JwtConfig {

	@Bean
	JwtTokenService jwtTokenService() {
		return new DefaultJwtTokenService();
	}

	@Bean
	JwtTokenProvider jwtTokenProvider(JwtTokenService jwtTokenService) {
		return new DefaultJwtTokenProvider(jwtTokenService);
	}

	@Bean
	BearerJWTTokenAuthInterceptor bearerJWTTokenAuthInterceptor(JwtTokenProvider jwtTokenProvider) {
		return new BearerJWTTokenAuthInterceptor(jwtTokenProvider);
	}
}
