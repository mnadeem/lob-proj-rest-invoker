package com.org.lob.support.security.jwt;

public interface JwtTokenService {

    JwtToken generateToken(String user);
}
