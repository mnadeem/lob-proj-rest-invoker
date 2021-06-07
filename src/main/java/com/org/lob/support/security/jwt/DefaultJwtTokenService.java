package com.org.lob.support.security.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class DefaultJwtTokenService implements JwtTokenService {

	private static final String ROLE_SYSTEM = "SYSTEM";
	private static final String CLAIM_ROLES = "roles";

	private final String jwtSecret;
	private final long tokenDurationInMinutes;

	public DefaultJwtTokenService(String jwtSecret, long tokenDurationInMinutes) {
		this.jwtSecret = jwtSecret;
		this.tokenDurationInMinutes = tokenDurationInMinutes;
	}

	@Override
	public JwtToken generateToken(String user) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, user);
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	private JwtToken doGenerateToken(Map<String, Object> claims, String subject) {
		long currentTime = System.currentTimeMillis();
		long expiration = currentTime + TimeUnit.MINUTES.toMillis(tokenDurationInMinutes);
		Date iat = new Date(currentTime);
		Date exp = new Date(expiration);

		String token = Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(iat)
				.setExpiration(exp)
				.claim(CLAIM_ROLES, Arrays.asList(ROLE_SYSTEM))
				.signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
				.compact();

		return new JwtToken(iat, exp, token);
	}
}
