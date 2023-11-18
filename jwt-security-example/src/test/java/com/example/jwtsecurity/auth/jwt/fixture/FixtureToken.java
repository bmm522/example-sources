package com.example.jwtsecurity.auth.jwt.fixture;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwtsecurity.auth.jwt.token.AccessToken;
import com.example.jwtsecurity.auth.jwt.token.RefreshToken;
import com.example.jwtsecurity.auth.jwt.token.Token;
import java.time.LocalDateTime;
import java.util.Date;
public class FixtureToken {

    public static Token createAccessToken() {
        return AccessToken.of(makeAccessJwt());
    }

    public static Token createRefreshToken() {
        return RefreshToken.of("testRefreshToken");
    }


    public static Token createAccessTokenByExpirationTime(final Long expirationTime) {
        return AccessToken.of(JWT.create()
            .withSubject(FixtureTokenMetadata.SUBJECT)
            .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
            .withClaim(FixtureTokenMetadata.CLAIM, FixtureTokenMetadata.USER_KEY)
            .sign(Algorithm.HMAC256(FixtureTokenMetadata.SECRET)));
    }



    private static String makeAccessJwt() {
        Date expiredAt = FixtureTokenMetadata.createTokenMetadata().getExpiresAtOfDateType(LocalDateTime.now());
        return JWT.create()
            .withSubject(FixtureTokenMetadata.SUBJECT)
            .withExpiresAt(expiredAt)
            .withClaim(FixtureTokenMetadata.CLAIM, FixtureTokenMetadata.USER_KEY)
            .sign(Algorithm.HMAC256(FixtureTokenMetadata.SECRET));
    }

}
