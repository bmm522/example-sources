package com.example.jwtsecurity.auth.jwt.token;

import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.AuthenticationAble;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RefreshTokenCreationStrategy implements TokenCreationStrategy {

        private final TokenProperties tokenProperties;

        private final TokenIssuer tokenIssuer;

        @Override
        public Token execute(AuthenticationAble authenticationAble) {

                String token = tokenIssuer.makeToken(
                    TokenMetadata.createWithOutClaim(authenticationAble, tokenProperties));

                return Token.of(tokenProperties.getRefreshTokenPrefix(), token);
        }

}
