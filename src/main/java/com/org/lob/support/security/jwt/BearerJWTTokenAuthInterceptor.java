package com.org.lob.support.security.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.org.lob.support.token.JwtTokenProvider;

public class BearerJWTTokenAuthInterceptor implements ClientHttpRequestInterceptor {

	private JwtTokenProvider jwtTokenProvider;

	public BearerJWTTokenAuthInterceptor(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		HttpHeaders headers = request.getHeaders();
		if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
			headers.setBearerAuth(this.jwtTokenProvider.getJwtToken());
		}
		return execution.execute(request, body);
	}
}
