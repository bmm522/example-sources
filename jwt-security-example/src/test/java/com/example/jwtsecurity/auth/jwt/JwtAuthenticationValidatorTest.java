package com.example.jwtsecurity.auth.jwt;



import org.junit.jupiter.api.BeforeEach;
public class JwtAuthenticationValidatorTest {


    JwtAuthenticationValidator jwtAuthenticationValidator;



    @BeforeEach
    void setUp() {
        jwtAuthenticationValidator = new JwtAuthenticationValidator();

    }



//    @Test
//    @DisplayName("토큰이 만료되면 true를 반환한다.")
//    void test1() {
//        Token expiredToken = AccessToken.of(JWT.create()
//            .withSubject(subject)
//            .withExpiresAt(new Date(System.currentTimeMillis() - 1000000L))
//            .withClaim(claimName, userKey)
//            .sign(Algorithm.HMAC256(secret)));
//        boolean result = jwtAuthenticationValidator.isExpiredPayload(expiredToken);
//        assertThat(result).isTrue();
//    }
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
