package com.example.jwtsecurity.security.jwt;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.jwtsecurity.fixture.FixtureToken;
import com.example.jwtsecurity.fixture.FixtureTokenMetadata;
import com.example.jwtsecurity.security.jwt.token.Token;
import com.example.jwtsecurity.security.jwt.token.TokenDecoder;
import com.example.jwtsecurity.security.jwt.token.TokenPrefixWrapper;

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

/**
 * @see JwtValidator
 */
@ExtendWith(MockitoExtension.class)
public class JwtValidatorTest {

  @InjectMocks
  JwtValidator jwtValidator;

  @Mock
  TokenDecoder tokenDecoder;

  String secret;

  @BeforeEach
  void setUp () {
	secret = FixtureTokenMetadata.SECRET;
  }

  @Nested
  @DisplayName("토큰이")
  class TokenExpirationValidationTests {

	@ParameterizedTest(name = "parameterInfo: {1}")
	@MethodSource("provideExpiredTokens")
	@DisplayName("만료되면 true를 반환한다.")
	void shouldReturnTrueForExpiredTokens (final Token token, final String parameterInfo) {

	  when(tokenDecoder.isTokenExpired(any(), any())).thenReturn(true);

	  final boolean result = jwtValidator.isTokenExpired(token, secret);

	  assertThat(result).isTrue();
	}

	@ParameterizedTest(name = "parameterInfo: {1}")
	@MethodSource("provideNotExpiredTokens")
	@DisplayName("만료되지 않으면 false를 반환한다.")
	void shouldReturnFalseForNonExpiredTokens (final Token token, final String parameterInfo) {

	  when(tokenDecoder.isTokenExpired(any(Token.class), anyString())).thenReturn(false);

	  final boolean result = jwtValidator.isTokenExpired(token, secret);

	  assertThat(result).isFalse();
	}

	private static Stream<Arguments> provideExpiredTokens () {
	  return Stream.of(
		  Arguments.of(FixtureToken.createAccessTokenByExpirationTime(-10000000L), "만료된 AccessToken"),
		  Arguments.of(FixtureToken.createRefreshTokenByExpirationTime(-10000000L), "만료된 RefreshToken")
	  );
	}

	private static Stream<Arguments> provideNotExpiredTokens () {
	  return Stream.of(
		  Arguments.of(FixtureToken.createAccessTokenByExpirationTime(10000000L), "만료되지 않은 AccessToken"),
		  Arguments.of(FixtureToken.createRefreshTokenByExpirationTime(10000000L), "만료되지 않은 RefreshToken")
	  );
	}
  }

  @ParameterizedTest(name = "parameterInfo: {1}")
  @MethodSource("provideWrapTokens")
  @DisplayName("올바르지 않은 prefix가 들어오면 예외를 발생한다")
  void throwExceptionWhenNotValidInputPrefix (final Token token, final String parameterInfo) {
	final String wrongPrefix = "wrongPrefix ";

	assertThatThrownBy(() -> jwtValidator.validateCheckPrefix(token, wrongPrefix)).isInstanceOf(RuntimeException.class);
  }

  private static Stream<Arguments> provideWrapTokens () {
	final Token wrapPrefixAccessToken = TokenPrefixWrapper.of("testPrefix ", FixtureToken.createAccessToken());
	final Token wrapPrefixRefreshToken = TokenPrefixWrapper.of("testPrefix", FixtureToken.createRefreshToken());
	return Stream.of(
		Arguments.of(wrapPrefixAccessToken, "AccessToken"),
		Arguments.of(wrapPrefixRefreshToken, "RefreshToken")
	);
  }

}
