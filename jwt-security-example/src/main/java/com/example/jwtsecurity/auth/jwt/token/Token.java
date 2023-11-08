package com.example.jwtsecurity.auth.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Token {

        private String token;

        public static Token generateAccessToken(final String userKey,
            TokenProperties tokenProperties) {
                String accessToken = tokenProperties.getAccessTokenPrefix() + JWT.create()
                    .withSubject(tokenProperties.getAccessTokenSubject())
                    .withExpiresAt(
                        new Date(System.currentTimeMillis()
                            + tokenProperties.getAccessTokenExpirationTime()))
                    .withClaim(tokenProperties.getAccessTokenClaim(),
                        userKey)
                    .sign(Algorithm.HMAC256(tokenProperties.getSecret()));
                return new Token(accessToken);
        }

        public static Token generateRefreshToken (final String userKey, TokenProperties tokenProperties) {
                String refreshToken = tokenProperties.getRefreshTokenPrefix() + JWT.create()
                    .withSubject(tokenProperties.getRefreshTokenSubject())
                    .withExpiresAt(new Date(
                        System.currentTimeMillis() + tokenProperties.getRefreshTokenExpirationTime()))
                    .sign(Algorithm.HMAC256(tokenProperties.getSecret()));
                return new Token(refreshToken);
        }
}
