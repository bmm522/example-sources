package com.example.jwtsecurity.auth.jwt.token;

import com.example.jwtsecurity.auth.jwt.AuthenticationAble;

import com.example.jwtsecurity.auth.jwt.JwtTokenProperties;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import java.util.Objects;

public record TokenMetadata(
    String userKey,
    String secret,
    String subject,
    Long expirationTime,
    String prefix,
    String claim) {

    public static TokenMetadata of(final String userKey, final String secret,
        final String subject, final Long expirationTime, final String prefix, final String claim) {
        validateCheckExpiresAt(expirationTime);
        return new TokenMetadata(userKey, secret, subject, expirationTime, prefix, claim);
    }

    public static TokenMetadata createAccessTokenMetadata(
        AuthenticationAble authenticationAble,
        JwtTokenProperties jwtTokenProperties) {
        return TokenMetadata.of(authenticationAble.getUserKey(),
            jwtTokenProperties.getSecretKey(),
            jwtTokenProperties.getAccessTokenSubject(),
            jwtTokenProperties.getAccessTokenExpirationTime(),
            jwtTokenProperties.getAccessTokenPrefix(),
            jwtTokenProperties.getAccessTokenClaim());
    }

    public static TokenMetadata createRefreshTokenMetadata(
        AuthenticationAble authenticationAble,
        JwtTokenProperties jwtTokenProperties) {
        return TokenMetadata.of(authenticationAble.getUserKey(),
            jwtTokenProperties.getSecretKey(),
            jwtTokenProperties.getRefreshTokenSubject(),
            jwtTokenProperties.getRefreshTokenExpirationTime(),
            jwtTokenProperties.getRefreshTokenPrefix(),
            null);
    }

    public boolean isExistClaim() {
        return Objects.nonNull(claim);
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
