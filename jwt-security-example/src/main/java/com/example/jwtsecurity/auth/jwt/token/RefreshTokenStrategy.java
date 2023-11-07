package com.example.jwtsecurity.auth.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwtsecurity.auth.AuthenticationAble;
import java.util.Date;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RefreshTokenStrategy implements TokenStrategy {

        private final TokenProperties tokenProperties;
        @Override
        public String generateToken(final AuthenticationAble authenticationAble) {
                return tokenProperties.getRefreshTokenPrefix() + JWT.create()
                    .withSubject(tokenProperties.getRefreshTokenSubject())
                    .withExpiresAt(new Date(
                        System.currentTimeMillis() + tokenProperties.getRefreshTokenExpirationTime()))
                    .sign(Algorithm.HMAC256(tokenProperties.getSecret()));
        }

}
