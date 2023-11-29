package com.example.jwtsecurity.security.jwt.token;

public class TokenPrefixWrapper {

  public static Token of (final String prefix, final Token token) {
	token.wrapPrefix(prefix);
	return token;
  }
}
