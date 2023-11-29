package com.example.jwtsecurity.security.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Auth0TokenDecoder implements TokenDecoder {

	public boolean isTokenExpired (final Token token, final String secretKey) {
		try {
			final DecodedJWT decodedJWT = decodedJWT(token, secretKey);
			final Date expirationTime = decodedJWT.getExpiresAt();
			return expirationTime.before(new Date());
		} catch (TokenExpiredException e) {
			return true;
		}
	}
	public DecodedJWT decodedJWT (final Token token, final String secretKey) {
		return JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token.getValue());
	}

}
