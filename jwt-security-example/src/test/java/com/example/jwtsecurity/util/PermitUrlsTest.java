package com.example.jwtsecurity.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.jwtsecurity.security.PermitUrls;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @see PermitUrls
 */
public class PermitUrlsTest {

	@ParameterizedTest(name = "url: {0}")
	@MethodSource("providePermittedUrls")
	@DisplayName("허용된 url이면 true를 반환한다.")
	void returnTrueWhenPermittedUrl (String url) {
		final boolean result = PermitUrls.isPermitted(url);

		assertThat(result).isTrue();
	}

	@ParameterizedTest(name = "url: {0}")
	@MethodSource("provideNotPermittedUrls")
	@DisplayName("허용되지 않은 url이면 false를 반환한다.")
	void returnFalseWhenNotPermittedUrl (String url) {
		final boolean result = PermitUrls.isPermitted(url);

		assertThat(result).isFalse();
	}

	private static Stream<Arguments> providePermittedUrls () {
		return Stream.of(
			Arguments.of("/login"),
			Arguments.of("/user/check-duplicate")
		);
	}

	private static Stream<Arguments> provideNotPermittedUrls () {
		return Stream.of(
			Arguments.of("/asdasdfafasf"),
			Arguments.of("/sfdhsfgasdfasfd")
		);
	}
}
