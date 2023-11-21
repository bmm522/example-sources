package com.example.jwtsecurity.auth.jwt;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.jwtsecurity.auth.jwt.fixture.FixtureToken;
import com.example.jwtsecurity.auth.jwt.fixture.FixtureTokenMetadata;
import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenDecoder;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
public class JwtValidatorTest {


    @InjectMocks
    JwtValidator jwtValidator;

    @Mock
    TokenDecoder tokenDecoder;

    String secret;


    @BeforeEach
    void setUp() {
        secret = FixtureTokenMetadata.SECRET;
    }


    @Nested
    @DisplayName("토큰이")
    class TokenExpirationValidationTests {
        @ParameterizedTest(name = "parameterInfo: {1}")
        @MethodSource("provideExpiredTokens")
        @DisplayName("만료되면 true를 반환한다.")
        void shouldReturnTrueForExpiredTokens(Token token, String parameterInfo) {

            when(tokenDecoder.isTokenExpired(any(), any())).thenReturn(true);

            boolean result = jwtValidator.isTokenExpired(token, secret);

            assertThat(result).isTrue();
        }

        @ParameterizedTest(name = "parameterInfo: {1}")
        @MethodSource("provideNotExpiredTokens")
        @DisplayName("만료되지 않으면 false를 반환한다.")
        void shouldReturnFalseForNonExpiredTokens(Token token, String parameterInfo) {

            when(tokenDecoder.isTokenExpired(any(Token.class), anyString())).thenReturn(false);

            boolean result = jwtValidator.isTokenExpired(token,secret);

            assertThat(result).isFalse();
        }

        private static Stream<Arguments> provideExpiredTokens() {
            return Stream.of(
                Arguments.of(FixtureToken.createAccessTokenByExpirationTime(-10000000L), "만료된 AccessToken"),
                Arguments.of(FixtureToken.createRefreshTokenByExpirationTime(-10000000L), "만료된 RefreshToken")
            );
        }

        private static Stream<Arguments> provideNotExpiredTokens() {
            return Stream.of(
                Arguments.of(FixtureToken.createAccessTokenByExpirationTime(10000000L), "만료되지 않은 AccessToken"),
                Arguments.of(FixtureToken.createRefreshTokenByExpirationTime(10000000L), "만료되지 않은 RefreshToken")
            );
        }
    }





}
