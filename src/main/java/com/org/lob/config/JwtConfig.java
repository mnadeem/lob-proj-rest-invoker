package com.org.lob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.org.lob.support.security.jwt.BearerJWTTokenAuthInterceptor;
import com.org.lob.support.security.jwt.DefaultJwtTokenService;
import com.org.lob.support.security.jwt.JwtTokenService;
import com.org.lob.support.token.DefaultJwtTokenProvider;
import com.org.lob.support.token.JwtTokenProvider;

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
