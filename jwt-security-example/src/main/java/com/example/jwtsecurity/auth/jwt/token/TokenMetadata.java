package com.example.jwtsecurity.auth.jwt.token;

import com.example.jwtsecurity.auth.AuthenticationAble;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.util.ObjectUtils;

public record TokenMetadata(
    String userKey,
    String secret,
    String subject,
    long expirationTime,
    String claim) {

        public static TokenMetadata of(final String userKey, final String secret,
            final String subject, final long expirationTime, final String claim) {

                return new TokenMetadata(userKey, secret, subject, expirationTime, claim);
        }
        public static TokenMetadata createWithClaim(AuthenticationAble authenticationAble,
            TokenProperties tokenProperties) {

                return new TokenMetadata(authenticationAble.getUserKey(),
                    tokenProperties.getSecret(),
                    tokenProperties.getAccessTokenSubject(),
                    tokenProperties.getAccessTokenExpirationTime(),
                    tokenProperties.getAccessTokenClaim());
        }
        public static TokenMetadata createWithOutClaim(AuthenticationAble authenticationAble,
            TokenProperties tokenProperties) {

                return new TokenMetadata(authenticationAble.getUserKey(),
                    tokenProperties.getSecret(),
                    tokenProperties.getRefreshTokenSubject(),
                    tokenProperties.getRefreshTokenExpirationTime(),
                    null);
        }

        public boolean isExistClaim() {

                System.out.println(claim);
                return !ObjectUtils.isEmpty(claim);
        }

        public Date getExpiresAtOfDateType(LocalDateTime now) {
                LocalDateTime expirationDateTime = now.plusNanos(this.expirationTime * 1000000);
                return Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }

}
