package com.example.jwtsecurity.fixture;

import jakarta.servlet.http.Cookie;

public class FixtureCookie {

  public static final String ACCESS_TOKEN_COOKIE_NAME = "AccessToken";

  public static final String REFRESH_TOKEN_COOKIE_NAME = "RefreshToken";

  public static final String ACCESS_TOKEN_COOKIE_VALUE = "accessTokenValue";

  public static final String REFRESH_TOKEN_COOKIE_VALUE = "refreshTokenValue";

  public static Cookie[] createCookies () {
	return new Cookie[] {
		new Cookie(ACCESS_TOKEN_COOKIE_NAME, ACCESS_TOKEN_COOKIE_VALUE),
		new Cookie(REFRESH_TOKEN_COOKIE_NAME, REFRESH_TOKEN_COOKIE_VALUE),
	};
  }

  public static Cookie[] createCookies (final String value) {
	return new Cookie[] {
		new Cookie(ACCESS_TOKEN_COOKIE_NAME, value),
		new Cookie(REFRESH_TOKEN_COOKIE_NAME, value),
	};
  }

  public static Cookie[] createCookies (final String accessTokenValue, final String refreshTokenValue) {
	return new Cookie[] {
		new Cookie(ACCESS_TOKEN_COOKIE_NAME, accessTokenValue),
		new Cookie(REFRESH_TOKEN_COOKIE_NAME, refreshTokenValue),
	};
  }
}
