package com.example.jwtsecurity.auth.jwt.token;

import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.AuthenticationAble;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AccessTokenCreationStrategy implements TokenCreationStrategy {

	private final TokenProperties tokenProperties;

	private final TokenIssuer tokenIssuer;

	@Override
	public Token execute (AuthenticationAble authenticationAble) {
		Token token = tokenIssuer.makeToken(
		  TokenMetadata.createWithClaim(authenticationAble, tokenProperties));
		token.setPrefix(tokenProperties.getAccessTokenPrefix());
		return token;
	}

}
