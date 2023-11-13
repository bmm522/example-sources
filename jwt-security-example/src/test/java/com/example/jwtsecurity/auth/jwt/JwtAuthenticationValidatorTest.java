package com.example.jwtsecurity.auth.jwt;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwtsecurity.auth.Payload;
import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenTestFixture;
import java.time.LocalDateTime;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class JwtAuthenticationValidatorTest extends TokenTestFixture {


    JwtAuthenticationValidator jwtAuthenticationValidator;



    @BeforeEach
    void setUp() {
        jwtAuthenticationValidator = new JwtAuthenticationValidator();

    }



    @Test
    @DisplayName("토큰이 만료되면 true를 반환한다.")
    void test1() {
        Token expiredToken = Token.of(JWT.create()
            .withSubject(subject)
            .withExpiresAt(new Date(System.currentTimeMillis() - 1000000L))
            .withClaim(claimName, userKey)
            .sign(Algorithm.HMAC256(secret)));
        boolean result = jwtAuthenticationValidator.isExpiredPayload(expiredToken);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("토큰이 만료되지 않으면 false를 반환한다.")
    void test2() {
        Token notExpiredToken = Token.of(JWT.create()
            .withSubject(subject)
            .withExpiresAt(new Date(System.currentTimeMillis() + 1000000L))
            .withClaim(claimName, userKey)
            .sign(Algorithm.HMAC256(secret)));

        boolean result = jwtAuthenticationValidator.isExpiredJwtToken(notExpiredToken);
        assertThat(result).isFalse();
    }
//
//    @Test
//    @DisplayName("유효하지 않은 토큰이면 예외를 반환한다.")
//    void test3 () {
//
//    }
}
