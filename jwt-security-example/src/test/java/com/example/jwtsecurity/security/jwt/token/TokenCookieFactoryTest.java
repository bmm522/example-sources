package com.example.jwtsecurity.security.jwt.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.example.jwtsecurity.security.jwt.fixture.FixtureToken;
import javax.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TokenCookieFactoryTest {

    TokenCookieFactory tokenCookieFactory;

    @BeforeEach
    void setUp() {
        tokenCookieFactory = new TokenCookieFactory();
    }

    @Test
    @DisplayName("AccessToken 타입의 토큰을 넣으면 해당 정보의 쿠키를 반환한다")
    void returnAccessTokenCookieWhenInputAccessTokenType() {
        Token accessToken = FixtureToken.createAccessToken();

        Cookie actual = tokenCookieFactory.of(accessToken);

        assertSoftly(softly -> {
            softly.assertThat(actual.getName()).isEqualTo(AccessToken.class.getSimpleName());
            softly.assertThat(actual.getMaxAge()).isEqualTo(10);
        });
    }

    @Test
    @DisplayName("RefreshToken 타입의 토큰을 넣으면 해당 정보의 쿠키를 반환한다")
    void returnRefreshTokenCookieWhenInputRefreshTokenType() {
        Token refreshToken = FixtureToken.createRefreshToken();

        Cookie actual = tokenCookieFactory.of(refreshToken);

        assertSoftly(softly -> {
            softly.assertThat(actual.getName()).isEqualTo(RefreshToken.class.getSimpleName());
            softly.assertThat(actual.getMaxAge()).isEqualTo(20);
        });
    }

    @Test
    @DisplayName("맞는 토큰 타입이 없으면 예외를 반환한다.")
    void returnExceptionWhenNotCorrectType() {
        Token given = new Token() {
            @Override
            public void wrapPrefix(final String prefix) {

            }
            @Override
            public String getValue() {
                return null;
            }
            @Override
            public boolean isStartsWith(final String prefix) {
                return false;
            }
            @Override
            public boolean isNotStartsWith(final String prefix) {
                return false;
            }
        };

        assertThatThrownBy(() -> tokenCookieFactory.of(given)).isInstanceOf(RuntimeException.class);
    }

}
