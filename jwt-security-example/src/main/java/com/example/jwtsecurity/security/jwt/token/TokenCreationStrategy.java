package com.example.jwtsecurity.security.jwt.token;



public interface TokenCreationStrategy {

        Token execute(TokenMetadata tokenMetadata);

}
