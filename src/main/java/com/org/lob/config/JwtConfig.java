package com.org.lob.config;

import org.springframework.beans.factory.annotation.Value;
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
	JwtTokenService jwtTokenService(@Value("${app.jwt.secret}") String jwtSecret, @Value("${app.jwt.token_duration.minutes}") long tokenDurationInMinutes) {
		return new DefaultJwtTokenService(jwtSecret, tokenDurationInMinutes);
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
