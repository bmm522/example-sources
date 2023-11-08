package com.example.jwtsecurity.auth.jwt.token;

public interface TokenCreationStrategy {

        Token makeToken(final TokenMetadata tokenMetadata);

}
