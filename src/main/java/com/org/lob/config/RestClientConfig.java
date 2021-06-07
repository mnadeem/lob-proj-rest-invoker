package com.org.lob.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.org.lob.support.security.jwt.BearerJWTTokenAuthInterceptor;

@Configuration
public class RestClientConfig {

	@Bean(name = "otherSystemRestTemplate")
	RestTemplate restTemplate(RestTemplateBuilder builder, BearerJWTTokenAuthInterceptor bearerJWTTokenAuthInterceptor) {
		return builder.additionalInterceptors(bearerJWTTokenAuthInterceptor).build();
	}
}
