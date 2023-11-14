package com.example.jwtsecurity.auth.jwt.token;

public interface TokenIssuer {

        String issue(TokenMetadata tokenMetadata);

}
