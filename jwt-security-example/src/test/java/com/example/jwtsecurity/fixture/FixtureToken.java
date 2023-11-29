package com.example.jwtsecurity.fixture;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwtsecurity.security.jwt.token.AccessToken;
import com.example.jwtsecurity.security.jwt.token.RefreshToken;
import com.example.jwtsecurity.security.jwt.token.Token;

import java.util.Date;

public class FixtureToken {

	public static Token createAccessToken () {
		return AccessToken.of(JWT.create()
			.withSubject(FixtureTokenMetadata.SUBJECT)
			.withExpiresAt(new Date(System.currentTimeMillis() + FixtureTokenMetadata.EXPIRATION_AT))
			.withClaim(FixtureTokenMetadata.CLAIM, FixtureTokenMetadata.USER_KEY)
			.sign(Algorithm.HMAC256(FixtureTokenMetadata.SECRET)));
	}

	public static Token createRefreshToken () {
		return RefreshToken.of(JWT.create()
			.withSubject(FixtureTokenMetadata.SUBJECT)
			.withExpiresAt(new Date(System.currentTimeMillis() + FixtureTokenMetadata.EXPIRATION_AT))
			.sign(Algorithm.HMAC256(FixtureTokenMetadata.SECRET)));
	}

	public static Token createAccessTokenByExpirationTime (final Long expirationTime) {
		return AccessToken.of(JWT.create()
			.withSubject(FixtureTokenMetadata.SUBJECT)
			.withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
			.withClaim(FixtureTokenMetadata.CLAIM, FixtureTokenMetadata.USER_KEY)
			.sign(Algorithm.HMAC256(FixtureTokenMetadata.SECRET)));
	}

	public static Token createRefreshTokenByExpirationTime (final Long expirationTime) {
		return RefreshToken.of(JWT.create()
			.withSubject(FixtureTokenMetadata.SUBJECT)
			.withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
			.sign(Algorithm.HMAC256(FixtureTokenMetadata.SECRET)));
	}

}
