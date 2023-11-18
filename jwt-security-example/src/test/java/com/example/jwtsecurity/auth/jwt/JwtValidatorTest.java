package com.example.jwtsecurity.auth.jwt;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.jwtsecurity.auth.jwt.fixture.FixtureToken;
import com.example.jwtsecurity.auth.jwt.fixture.FixtureTokenMetadata;
import com.example.jwtsecurity.auth.jwt.token.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class JwtValidatorTest {


    JwtValidator jwtValidator;



    @BeforeEach
    void setUp() {
        jwtValidator = new JwtValidator();

    }



    @Test
    @DisplayName("Access 토큰이 만료되면 true를 반환한다.")
    void test1() {
        Token expiredAccessToken = FixtureToken.createAccessTokenByExpirationTime(-10000000L);

        boolean result = jwtValidator.isExpiredAccessToken(expiredAccessToken, FixtureTokenMetadata.SECRET);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Access 토큰이 만료되지 않으면 false를 반환한다.")
    void test2() {
        Token notExpiredAccessToken = FixtureToken.createAccessTokenByExpirationTime(10000000L);

        boolean result = jwtValidator.isExpiredAccessToken(notExpiredAccessToken, FixtureTokenMetadata.SECRET);
        assertThat(result).isFalse();
    }
//
//    @Test
//    @DisplayName("토큰이 만료되지 않으면 false를 반환한다.")
//    void test2() {
//        Token notExpiredToken = AccessToken.of(JWT.create()
//            .withSubject(subject)
//            .withExpiresAt(new Date(System.currentTimeMillis() + 1000000L))
//            .withClaim(claimName, userKey)
//            .sign(Algorithm.HMAC256(secret)));
//
//        boolean result = jwtAuthenticationValidator.isExpiredJwtToken(notExpiredToken);
//        assertThat(result).isFalse();
//    }
//
//    @Test
//    @DisplayName("유효하지 않은 토큰이면 예외를 반환한다.")
//    void test3 () {
//
//    }
}
