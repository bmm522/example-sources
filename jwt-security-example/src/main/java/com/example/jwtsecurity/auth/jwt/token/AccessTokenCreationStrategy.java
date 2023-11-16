package com.example.jwtsecurity.auth.jwt.token;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AccessTokenCreationStrategy implements TokenCreationStrategy {

    private final TokenIssuer tokenIssuer;

    @Override
    public Token execute(TokenMetadata tokenMetadata) {
        String token = tokenIssuer.issue(tokenMetadata);
        return AccessToken.of(token);
    }

}
