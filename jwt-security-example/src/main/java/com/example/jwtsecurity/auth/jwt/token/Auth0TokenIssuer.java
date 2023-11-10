package com.example.jwtsecurity.auth.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.stereotype.Component;

@Component
public class Auth0TokenIssuer implements TokenIssuer {

        @Override
        public Token makeToken(final TokenMetadata tokenMetadata) {

                Builder jwtBuilder = JWT.create().withSubject(tokenMetadata.subject())
                    .withExpiresAt(tokenMetadata.getExpiresAtOfDateType());

                if (tokenMetadata.isExistClaim()) {
                        jwtBuilder.withClaim(tokenMetadata.claim(), tokenMetadata.userKey());
                }

                String token = jwtBuilder.sign(Algorithm.HMAC256(tokenMetadata.secret()));
                return Token.from(tokenMetadata.tokenPrefix() + token);

        }

}
