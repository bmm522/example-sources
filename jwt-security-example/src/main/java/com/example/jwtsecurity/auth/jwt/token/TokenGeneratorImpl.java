package com.example.jwtsecurity.auth.jwt.token;


import com.example.jwtsecurity.auth.AuthenticationAble;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenGeneratorImpl implements TokenGenerator {

        private final TokenProperties tokenProperties;

        private final TokenCreationStrategy tokenCreationStrategy;

        public Token generateAccessToken(AuthenticationAble authenticationAble) {

                try {
                        return tokenCreationStrategy.makeToken(
                            TokenMetadata.of(authenticationAble.getUserKey(),
                                tokenProperties.getSecret(),
                                tokenProperties.getAccessTokenSubject(),
                                tokenProperties.getAccessTokenExpirationTime(),
                                tokenProperties.getAccessTokenClaim(),
                                tokenProperties.getAccessTokenPrefix())
                        );
                } catch (Exception e) {
                        throw new RuntimeException("Access Token Generate Error");
                }
        }

        public Token generateRefreshToken(AuthenticationAble authenticationAble) {

                try {
                        return tokenCreationStrategy.makeToken(
                            TokenMetadata.of(authenticationAble.getUserKey(),
                                tokenProperties.getSecret(),
                                tokenProperties.getRefreshTokenSubject(),
                                tokenProperties.getRefreshTokenExpirationTime(),
                                null,
                                tokenProperties.getRefreshTokenPrefix())
                        );
                } catch (Exception e) {
                        throw new RuntimeException("Access Token Generate Error");
                }
        }


}
