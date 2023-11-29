package com.example.jwtsecurity.security.jwt.token;

public interface TokenCreationStrategy {

  Token execute (final TokenMetadata tokenMetadata);

  Token execute (final String value);
}
