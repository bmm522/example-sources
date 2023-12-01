package com.example.jwtsecurity.security.jwt;

import com.example.jwtsecurity.security.AuthenticationAble;
import com.example.jwtsecurity.security.jwt.token.Token;
import com.example.jwtsecurity.security.jwt.token.TokenMetadata;

import jakarta.servlet.http.Cookie;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtService {

  private final JwtDecoder jwtDecoder;

  private final JwtTokenProperties jwtTokenProperties;

  private final JwtPayloadGenerator jwtPayloadGenerator;

  public JwtPayload generatePayload (AuthenticationAble authenticationAble) {
	TokenMetadata accessTokenMetadata = getAccessTokenMetadata(authenticationAble);
	TokenMetadata refreshTokenMetadata = getRefreshTokenMetadata(authenticationAble);
	return jwtPayloadGenerator.generate(accessTokenMetadata, refreshTokenMetadata);
  }

  public JwtPayload generatePayload (Cookie[] cookies) {
	return jwtPayloadGenerator.generate(cookies, jwtTokenProperties.getAccessTokenCookieName(),
		jwtTokenProperties.getRefreshTokenCookieName());
  }

  private TokenMetadata getAccessTokenMetadata (AuthenticationAble authenticationAble) {
	return TokenMetadata.createAccessTokenMetadata(authenticationAble, jwtTokenProperties);
  }

  private TokenMetadata getRefreshTokenMetadata (AuthenticationAble authenticationAble) {
	return TokenMetadata.createRefreshTokenMetadata(authenticationAble, jwtTokenProperties);
  }

  public boolean isAccessTokenExpired (JwtPayload jwtPayload) {
	jwtDecoder.validateCheckPrefix(jwtPayload.getAccessToken(), jwtTokenProperties.getAccessTokenPrefix());
	return jwtDecoder.isTokenExpired(jwtPayload.getAccessToken(), jwtTokenProperties.getSecretKey());
  }

  public String getUserIdFromAccessToken (Token accessToken) {
	return jwtDecoder.getUserIdFromToken(accessToken, jwtTokenProperties.getAccessTokenClaim(), jwtTokenProperties.getSecretKey());
  }
}
