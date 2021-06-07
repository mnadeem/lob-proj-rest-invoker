package com.org.lob.support.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.lob.support.security.jwt.JwtToken;
import com.org.lob.support.security.jwt.JwtTokenService;

public class DefaultJwtTokenProvider implements JwtTokenProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultJwtTokenProvider.class);

	private static final String USER_NAME_PROJECT = "PROJECT";

	private final JwtTokenService jwtTokenService;
	private JwtToken jwtToken;

	public DefaultJwtTokenProvider(JwtTokenService jwtTokenService) {
		this.jwtTokenService = jwtTokenService;
		this.jwtToken = jwtTokenService.generateToken(USER_NAME_PROJECT);
	}

	@Override
	public String getJwtToken() {
		if (this.jwtToken.isExpired()) {
			refreshToken();
		}

		return jwtToken.getToken();
	}

	private void refreshToken() {
		LOGGER.debug("Token Experied, Refreshing token");
		this.jwtToken = jwtTokenService.generateToken(USER_NAME_PROJECT);
	}
}
