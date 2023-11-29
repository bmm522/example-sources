package com.example.jwtsecurity.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import jakarta.servlet.http.Cookie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @see CookieIssuer
 */
public class CookieIssuerTest {

  @Test
  @DisplayName("해당 쿠키 이름과, 값과, 만료시간을 넣으면 그에 맞는 쿠키를 반환한다")
  void executeTest () {
	final String testName = "testName";
	final String testValue = "testValue";
	final int testExpiry = 20;
	final Cookie result = CookieIssuer.execute(testName, testValue, testExpiry);

	assertSoftly(softly -> {
	  softly.assertThat(result.getName()).isEqualTo(testName);
	  softly.assertThat(result.getValue()).isEqualTo(testValue);
	  softly.assertThat(result.getMaxAge()).isEqualTo(testExpiry);
	});
  }
}
