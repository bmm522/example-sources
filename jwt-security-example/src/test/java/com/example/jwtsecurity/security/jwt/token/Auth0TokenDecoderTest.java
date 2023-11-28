package com.example.jwtsecurity.security.jwt.token;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.example.jwtsecurity.fixture.FixtureToken;
import com.example.jwtsecurity.fixture.FixtureTokenMetadata;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
/**
 * @see Auth0TokenDecoder
 */
public class Auth0TokenDecoderTest {

    String secret;

    TokenDecoder tokenDecoder;

    @BeforeEach
    void setUp() {
        secret = FixtureTokenMetadata.SECRET;
        tokenDecoder = new Auth0TokenDecoder();
    }

    @Nested
    @DisplayName("토큰이")
    class TokenExpirationValidationTests {
        @ParameterizedTest(name = "parameterInfo: {1}")
        @MethodSource("provideExpiredTokens")
        @DisplayName("만료되면 true를 반환한다.")
        void shouldReturnTrueForExpiredTokens(Token token, String parameterInfo) {
            boolean result = tokenDecoder.isTokenExpired(token, secret);

            assertThat(result).isTrue();
        }

        @ParameterizedTest(name = "parameterInfo: {1}")
        @MethodSource("provideNotExpiredTokens")
        @DisplayName("만료되지 않으면 false를 반환한다.")
        void shouldReturnFalseForNonExpiredTokens(Token token, String parameterInfo) {
            boolean result = tokenDecoder.isTokenExpired(token,secret);

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
