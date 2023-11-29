package com.example.jwtsecurity.security.jwt.token;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface TokenDecoder {

	boolean isTokenExpired (final Token token, final String secretKey);

	DecodedJWT decodedJWT (final Token token, final String secretKey);

}
