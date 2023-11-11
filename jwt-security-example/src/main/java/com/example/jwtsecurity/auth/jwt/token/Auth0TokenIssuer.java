package com.example.jwtsecurity.auth.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class Auth0TokenIssuer implements TokenIssuer {

        @Override
        public String makeToken(final TokenMetadata tokenMetadata) {

                Builder jwtBuilder = JWT.create()
                    .withSubject(tokenMetadata.subject())
                    .withExpiresAt(tokenMetadata.getExpiresAtOfDateType(LocalDateTime.now()));

                if (tokenMetadata.isExistClaim()) {
                        jwtBuilder.withClaim(tokenMetadata.claim(), tokenMetadata.userKey());
                }

                return jwtBuilder.sign(Algorithm.HMAC256(tokenMetadata.secret()));
        }

}
