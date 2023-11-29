package com.example.jwtsecurity.security.jwt.token;

import com.example.jwtsecurity.util.CookieIssuer;

import com.example.jwtsecurity.util.CookieParser;

import jakarta.servlet.http.Cookie;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class TokenCookieHandler {

	public Cookie createCookie (final Token token) {
		if (token instanceof AccessToken) {
			return CookieIssuer.execute(AccessToken.class.getSimpleName(), token.getValue(), 10);
		}

		if (token instanceof RefreshToken) {
			return CookieIssuer.execute(RefreshToken.class.getSimpleName(), token.getValue(), 20);
		}

		throw new RuntimeException("일치하는 Token 타입이 없습니다.");
	}

	public Token getAccessTokenFromCookies (final Cookie[] cookies, final String accessTokenName) {
		return CookieParser.getValue(cookies, accessTokenName)
			.map(AccessToken::of)
			.orElse(null);
	}

	public Token getRefreshTokenFromCookies (final Cookie[] cookies,final String refreshTokenName) {
		return CookieParser.getValue(cookies, refreshTokenName)
			.map(RefreshToken::of)
			.orElse(null);
	}

}
