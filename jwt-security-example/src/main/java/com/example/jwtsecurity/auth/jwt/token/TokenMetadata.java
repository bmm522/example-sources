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
    String prefix,
    String claim) {

    public static TokenMetadata of(final String userKey, final String secret,
        final String subject, final long expirationTime, final String prefix, final String claim) {
        validateCheckExpiresAt(expirationTime);
        return new TokenMetadata(userKey, secret, subject, expirationTime, prefix, claim);
    }

    public static TokenMetadata createAccessTokenMetadata(AuthenticationAble authenticationAble,
        TokenProperties tokenProperties) {
        return TokenMetadata.of(authenticationAble.getUserKey(),
            tokenProperties.getSecretKey(),
            tokenProperties.getAccessTokenSubject(),
            tokenProperties.getAccessTokenExpirationTime(),
            tokenProperties.getAccessTokenPrefix(),
            tokenProperties.getAccessTokenClaim());
    }

    public static TokenMetadata createRefreshTokenMetadata(AuthenticationAble authenticationAble,
        TokenProperties tokenProperties) {
        return TokenMetadata.of(authenticationAble.getUserKey(),
            tokenProperties.getSecretKey(),
            tokenProperties.getRefreshTokenSubject(),
            tokenProperties.getRefreshTokenExpirationTime(),
            tokenProperties.getRefreshTokenPrefix(),
            null);
    }

    public boolean isExistClaim() {
        return !ObjectUtils.isEmpty(claim);
    }

    public Date getExpiresAtOfDateType(LocalDateTime now) {
        LocalDateTime expirationDateTime = now.plusNanos(this.expirationTime * 1000000);
        return Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private static void validateCheckExpiresAt(Long expirationTime) {
        if (expirationTime < 0) {
            throw new IllegalArgumentException("만료 시간에 음수는 들어 올 수 없습니다.");
        }
    }

}
