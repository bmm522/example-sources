package com.example.jwtsecurity.auth.jwt.token;

import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.AuthenticationAble;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TokenGeneratorImpl implements TokenGenerator {

	private TokenCreationStrategy tokenCreationStrategy;

	public Token generateAccessToken (AuthenticationAble authenticationAble) {
		return tokenCreationStrategy.makeToken(TokenType.ACCESS_TOKEN, authenticationAble);
	}

	public Token generateRefreshToken (AuthenticationAble authenticationAble) {
		return tokenCreationStrategy.makeToken(TokenType.REFRESH_TOKEN, authenticationAble);
	}

}
