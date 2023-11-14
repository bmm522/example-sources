package com.example.jwtsecurity.auth.jwt.token;



public interface TokenCreationStrategy {

        Token execute(TokenMetadata tokenMetadata);

}
