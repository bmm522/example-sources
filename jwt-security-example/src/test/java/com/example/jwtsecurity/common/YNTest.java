package com.example.jwtsecurity.common;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class YNTest {

	@ParameterizedTest(name = "isY에 {0}을 넣으면 {1}을 반환한다.")
	@MethodSource("provideIsYTestParameter")
	@DisplayName("statc isY 테스트")
	void staticIsYMethodTest (final String given,final boolean expect) {
		final boolean result = YN.isY(given);

		assertThat(result).isEqualTo(expect);
	}

	@ParameterizedTest(name = "isN에 {0}을 넣으면 {1}을 반환한다.")
	@MethodSource("provideIsNTestParameter")
	@DisplayName("statc isN 테스트")
	void staticIsNMethodTest (String given, boolean expect) {
		final boolean result = YN.isN(given);

		assertThat(result).isEqualTo(expect);
	}

	private static Stream<Arguments> provideIsYTestParameter () {
		return Stream.of(
			Arguments.of("Y", true),
			Arguments.of("N", false),
			Arguments.of("y", false),
			Arguments.of("n", false),
			Arguments.of("asd", false)
		);
	}

	private static Stream<Arguments> provideIsNTestParameter () {
		return Stream.of(
			Arguments.of("Y", false),
			Arguments.of("N", true),
			Arguments.of("y", false),
			Arguments.of("n", false),
			Arguments.of("asd", false)
		);
	}
}
