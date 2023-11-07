package com.example.jwtsecurity.auth.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwtsecurity.auth.AuthenticationAble;
import java.util.Date;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccessTokenStrategy implements TokenStrategy {

        private final TokenProperties tokenProperties;

        @Override
        public String generateToken(final AuthenticationAble authenticationAble) {
                return tokenProperties.getAccessTokenPrefix() + JWT.create()
                    .withSubject(tokenProperties.getAccessTokenSubject())
                    .withExpiresAt(
                        new Date(System.currentTimeMillis()
                            + tokenProperties.getAccessTokenExpirationTime()))
                    .withClaim(tokenProperties.getAccessTokenClaim(),
                        authenticationAble.getUserKey())
                    .sign(Algorithm.HMAC256(tokenProperties.getSecret()));
        }

}
