package com.example.jwtsecurity.auth.jwt.token;

public interface TokenGenerator {

        Token generate(TokenCreationStrategy tokenCreationStrategy,
           TokenMetadata tokenMetadata);

}
