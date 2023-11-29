package com.example.jwtsecurity.security.jwt.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.example.jwtsecurity.fixture.FixtureCookie;
import com.example.jwtsecurity.fixture.FixtureToken;

import jakarta.servlet.http.Cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @see TokenCookieHandler
 */
public class TokenCookieHandlerTest {

  TokenCookieHandler tokenCookieHandler;

  @BeforeEach
  void setUp () {
	tokenCookieHandler = new TokenCookieHandler();
  }

  @Nested
  @DisplayName("createCookie 메서드 테스트")
  class createCookieTests {

	@Test
	@DisplayName("AccessToken 타입의 토큰을 넣으면 해당 정보의 쿠키를 반환한다")
	void returnAccessTokenCookieWhenInputAccessTokenType () {
	  final Token accessToken = FixtureToken.createAccessToken();

	  final Cookie result = tokenCookieHandler.createCookie(accessToken);

	  assertSoftly(softly -> {
		softly.assertThat(result.getName()).isEqualTo(AccessToken.class.getSimpleName());
		softly.assertThat(result.getMaxAge()).isEqualTo(10);
	  });
	}

	@Test
	@DisplayName("RefreshToken 타입의 토큰을 넣으면 해당 정보의 쿠키를 반환한다")
	void returnRefreshTokenCookieWhenInputRefreshTokenType () {
	  final Token refreshToken = FixtureToken.createRefreshToken();

	  final Cookie result = tokenCookieHandler.createCookie(refreshToken);

	  assertSoftly(softly -> {
		softly.assertThat(result.getName()).isEqualTo(RefreshToken.class.getSimpleName());
		softly.assertThat(result.getMaxAge()).isEqualTo(20);
	  });
	}

	@Test
	@DisplayName("맞는 토큰 타입이 없으면 예외를 반환한다.")
	void returnExceptionWhenNotCorrectType () {
	  final Token given = new Token() {
		@Override
		public void wrapPrefix (final String prefix) {

		}
		@Override
		public String getValue () {
		  return null;
		}
		@Override
		public boolean isStartsWith (final String prefix) {
		  return false;
		}
		@Override
		public boolean isNotStartsWith (final String prefix) {
		  return false;
		}
	  };

	  assertThatThrownBy(() -> tokenCookieHandler.createCookie(given)).isInstanceOf(
		  RuntimeException.class);
	}

  }

  @Nested
  @DisplayName("getTokenFromCookie 테스트")
  class getTokenFromCookieTests {

	@Test
	@DisplayName("cookie 배열과 AccessToken의 쿠키 이름을 넣으면 해당 토큰을 반환한다.")
	void returnAccessTokenWhenInputCookieArrayAndAccessTokenName () {
	  final Cookie[] given = FixtureCookie.createCookies();

	  final Token result = tokenCookieHandler.getAccessTokenFromCookies(given, FixtureCookie.ACCESS_TOKEN_COOKIE_NAME);

	  assertThat(result).isInstanceOf(AccessToken.class);
	}

	@Test
	@DisplayName("cookie 배열과 RefreshToken의 쿠키 이름을 넣으면 해당 토큰을 반환한다.")
	void returnRefreshTokenWhenInputCookieArrayAndRefreshTokenName () {
	  final Cookie[] given = FixtureCookie.createCookies();

	  final Token result = tokenCookieHandler.getRefreshTokenFromCookies(given, FixtureCookie.REFRESH_TOKEN_COOKIE_NAME);

	  assertThat(result).isInstanceOf(RefreshToken.class);
	}
  }

}
