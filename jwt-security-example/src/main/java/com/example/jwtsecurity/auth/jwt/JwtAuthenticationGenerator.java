package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.jwt.token.AccessTokenCreationStrategy;
import com.example.jwtsecurity.auth.jwt.token.RefreshTokenCreationStrategy;
import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenGenerator;
import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.AuthenticationAble;
import com.example.jwtsecurity.auth.AuthenticationGenerator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationGenerator implements AuthenticationGenerator<JwtPayload> {

	private final TokenGenerator tokenGenerator;
	private final AccessTokenCreationStrategy accessTokenCreationStrategy;
	private final RefreshTokenCreationStrategy refreshTokenCreationStrategy;

	@Override
	public JwtPayload generate (AuthenticationAble authenticationAble) {
		Token accessToken = tokenGenerator.generate(accessTokenCreationStrategy, authenticationAble);
		Token refreshToken = tokenGenerator.generate(refreshTokenCreationStrategy, authenticationAble);
		return JwtPayload.of(accessToken.getToken(), refreshToken.getToken());
	}

}
