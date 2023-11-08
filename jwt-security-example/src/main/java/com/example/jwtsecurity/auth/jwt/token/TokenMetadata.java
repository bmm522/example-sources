package com.example.jwtsecurity.auth.jwt.token;

import org.springframework.util.ObjectUtils;

public record TokenMetadata(
    String userKey,
    String secret,
    String subject,
    long expirationTime,
    String claim,
    String tokenPrefix) {

        public static TokenMetadata of(final String userKey, final String secret,
            final String subject, final long expirationTime, final String claim,
            final String tokenPrefix) {

                return new TokenMetadata(userKey, secret, subject, expirationTime, claim,
                    tokenPrefix);
        }


        public boolean isExistClaim() {

                return !ObjectUtils.isEmpty(claim);
        }


}
