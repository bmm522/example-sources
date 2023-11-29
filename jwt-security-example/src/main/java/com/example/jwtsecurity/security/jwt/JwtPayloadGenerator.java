package com.example.jwtsecurity.security.jwt;

import com.example.jwtsecurity.security.jwt.token.AccessTokenCreationStrategy;
import com.example.jwtsecurity.security.jwt.token.RefreshTokenCreationStrategy;
import com.example.jwtsecurity.security.jwt.token.Token;
import com.example.jwtsecurity.security.jwt.token.TokenCookieHandler;
import com.example.jwtsecurity.security.jwt.token.TokenGenerator;
import com.example.jwtsecurity.security.jwt.token.TokenMetadata;

import jakarta.servlet.http.Cookie;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtPayloadGenerator {

	private final TokenGenerator tokenGenerator;

	private final AccessTokenCreationStrategy accessTokenCreationStrategy;

	private final RefreshTokenCreationStrategy refreshTokenCreationStrategy;

	private final TokenCookieHandler tokenCookieHandler;

	public JwtPayload generate (final TokenMetadata accessTokenMetadata, final TokenMetadata refreshTokenMetadata) {
		final Token accessToken = tokenGenerator.generate(accessTokenCreationStrategy, accessTokenMetadata);
		final Token refreshToken = tokenGenerator.generate(refreshTokenCreationStrategy, refreshTokenMetadata);
		return JwtPayload.of(accessToken, refreshToken);
	}

	public JwtPayload generate (final Cookie[] cookies, final String accessTokenCookieName, final String refreshTokenCookieName) {
		final Token accessToken = tokenCookieHandler.getAccessTokenFromCookies(cookies, accessTokenCookieName);
		final Token refreshToken = tokenCookieHandler.getRefreshTokenFromCookies(cookies, refreshTokenCookieName);
		return JwtPayload.of(accessToken, refreshToken);
	}

}
