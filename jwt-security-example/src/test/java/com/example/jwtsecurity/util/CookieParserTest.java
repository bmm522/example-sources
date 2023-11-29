package com.example.jwtsecurity.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.jwtsecurity.fixture.FixtureCookie;
import com.example.jwtsecurity.fixture.FixtureToken;

import jakarta.servlet.http.Cookie;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @see CookieParser
 */
public class CookieParserTest {

  @Test
  @DisplayName("해당 이름의 쿠키를 반환한다.")
  void returnCookieMatchedName () {
	final String accessTokenValue = FixtureToken.createAccessToken().getValue();
	final Cookie[] given = FixtureCookie.createCookies(accessTokenValue);

	final Optional<String> result = CookieParser.getValue(given, FixtureCookie.ACCESS_TOKEN_COOKIE_NAME);

	assertThat(result.get()).isEqualTo(accessTokenValue);
  }

  @Test
  @DisplayName("해당 이름에 맞는 쿠키가 없으면 빈 값을 반환한다.")
  void returnNullStringWhenNotMatchedName () {
	final Cookie[] given = FixtureCookie.createCookies();

	final Optional<String> result = CookieParser.getValue(given, "asdfsadfsadf");

	assertThat(result.isEmpty()).isTrue();
  }

  @Test
  @DisplayName("쿠키 배열에 null이 들어오면 빈 값을 반환한다.")
  void returnNullWhenInputNullCookieArray () {
	final Optional<String> result = CookieParser.getValue(null, FixtureCookie.ACCESS_TOKEN_COOKIE_NAME);

	assertThat(result.isEmpty()).isTrue();
  }

}
