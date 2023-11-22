package com.example.jwtsecurity.security.jwt.token;

public interface TokenGenerator {

        Token generate(TokenCreationStrategy tokenCreationStrategy,
           TokenMetadata tokenMetadata);

}
