package com.example.jwtsecurity.security.jwt.token;

public interface TokenGenerator {

  Token generate (final TokenCreationStrategy tokenCreationStrategy, final TokenMetadata tokenMetadata);

}
