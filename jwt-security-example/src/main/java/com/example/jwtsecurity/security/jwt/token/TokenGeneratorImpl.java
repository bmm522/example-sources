package com.example.jwtsecurity.security.jwt.token;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TokenGeneratorImpl implements TokenGenerator {

	@Override
	public Token generate (final TokenCreationStrategy tokenCreationStrategy, final TokenMetadata tokenMetadata) {
		final Token token = tokenCreationStrategy.execute(tokenMetadata);
		return TokenPrefixWrapper.of(tokenMetadata.prefix(), token);
	}

}
