package com.example.jwtsecurity.security.jwt.token;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RefreshTokenCreationStrategy implements TokenCreationStrategy {

	private final TokenIssuer tokenIssuer;

	@Override
	public Token execute (final TokenMetadata tokenMetadata) {
		final String token = tokenIssuer.issue(tokenMetadata);
		return RefreshToken.of(token);
	}
	@Override
	public Token execute (final String value) {
		return RefreshToken.of(value);
	}

}
