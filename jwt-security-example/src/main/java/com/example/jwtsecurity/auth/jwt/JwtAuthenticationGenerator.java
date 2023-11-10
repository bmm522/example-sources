package com.example.jwtsecurity.auth.jwt;

import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.AuthenticationAble;
import com.example.jwtsecurity.auth.AuthenticationGenerator;
import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenGenerator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationGenerator implements AuthenticationGenerator<JwtPayload> {

	private final TokenGenerator tokenGenerator;

	@Override
	public JwtPayload generate (AuthenticationAble authenticationAble) {
		Token accessToken = tokenGenerator.generateAccessToken(authenticationAble);
		Token refreshToken = tokenGenerator.generateRefreshToken(authenticationAble);
		return JwtPayload.of(accessToken.getToken(), refreshToken.getToken());
	}

}
