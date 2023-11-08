package com.example.jwtsecurity.auth.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

public class Auth0JWTCreationStrategy implements TokenCreationStrategy {

        @Override
        public Token makeToken(final TokenMetadata tokenMetadata) {

                Date expiresAt = new Date(
                    System.currentTimeMillis() + tokenMetadata.expirationTime());
                Builder jwtBuilder = JWT.create().withSubject(tokenMetadata.subject())
                    .withExpiresAt(expiresAt);

                if (tokenMetadata.isExistClaim()) {
                        jwtBuilder.withClaim(tokenMetadata.claim(), tokenMetadata.userKey());
                }

                String token = jwtBuilder.sign(Algorithm.HMAC256(tokenMetadata.secret()));
                return Token.from(tokenMetadata.tokenPrefix() + token);

        }

}
