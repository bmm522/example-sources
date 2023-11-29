package com.example.jwtsecurity.security.jwt;

import com.example.jwtsecurity.security.jwt.token.Token;
import com.example.jwtsecurity.security.jwt.token.TokenDecoder;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtValidator {

  private final TokenDecoder tokenDecoder;

  public boolean isTokenExpired (final Token token, final String secretKey) {
	return tokenDecoder.isTokenExpired(token, secretKey);
  }

  public void validateCheckPrefix (Token token, String prefix) {
	if (token.isNotStartsWith(prefix)) {
	  throw new RuntimeException("올바르지 않은 prefix 입니다.");
	}
  }

}
