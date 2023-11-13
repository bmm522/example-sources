package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.jwt.token.AccessTokenCreationStrategy;
import com.example.jwtsecurity.auth.jwt.token.RefreshTokenCreationStrategy;
import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenGenerator;
import com.example.jwtsecurity.auth.jwt.token.TokenMetadatas;
import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.PayloadGenerator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtPayloadGenerator implements PayloadGenerator<JwtPayload> {

	private final TokenGenerator tokenGenerator;
	private final AccessTokenCreationStrategy accessTokenCreationStrategy;
	private final RefreshTokenCreationStrategy refreshTokenCreationStrategy;

	@Override
	public JwtPayload generate (TokenMetadatas tokenMetadatas) {
		Token accessToken = tokenGenerator.generate(
			accessTokenCreationStrategy, tokenMetadatas.getAccessTokenMetadata());

		Token refreshToken = tokenGenerator.generate(
			refreshTokenCreationStrategy, tokenMetadatas.getRefreshTokenMetadata());
		return JwtPayload.of(accessToken, refreshToken);
	}

}
