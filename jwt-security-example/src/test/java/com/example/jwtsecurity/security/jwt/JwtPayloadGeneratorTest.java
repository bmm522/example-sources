package com.example.jwtsecurity.security.jwt;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.jwtsecurity.fixture.FixtureCookie;
import com.example.jwtsecurity.security.jwt.token.AccessTokenCreationStrategy;
import com.example.jwtsecurity.security.jwt.token.RefreshTokenCreationStrategy;
import com.example.jwtsecurity.security.jwt.token.Token;
import com.example.jwtsecurity.security.jwt.token.TokenCookieHandler;
import com.example.jwtsecurity.security.jwt.token.TokenGenerator;
import com.example.jwtsecurity.security.jwt.token.TokenMetadata;
import com.example.jwtsecurity.fixture.FixtureToken;
import com.example.jwtsecurity.fixture.FixtureTokenMetadata;

import jakarta.servlet.http.Cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @see JwtPayloadGenerator
 */
@ExtendWith(MockitoExtension.class)
public class JwtPayloadGeneratorTest {

  @Mock
  private TokenGenerator tokenGenerator;

  @Mock
  private TokenCookieHandler tokenCookieHandler;

  @Mock
  private AccessTokenCreationStrategy accessTokenCreationStrategy;

  @Mock
  private RefreshTokenCreationStrategy refreshTokenCreationStrategy;

  @InjectMocks
  private JwtPayloadGenerator jwtPayloadGenerator;

  Token accessToken;

  Token refreshToken;

  @BeforeEach
  void setUp () {
	accessToken = FixtureToken.createAccessToken();
	refreshToken = FixtureToken.createRefreshToken();
  }

  @Test
  @DisplayName("TokenMetadata를 넣으면 JwtPayload를 반환한다 ")
  void returnTokenMetadataWhenInputJwtPayload () {
	final TokenMetadata accessTokenMetadata = FixtureTokenMetadata.createAccessTokenMetadata();
	final TokenMetadata refreshTokenMetadata = FixtureTokenMetadata.createRefreshTokenMetadata();

	when(tokenGenerator.generate(accessTokenCreationStrategy, accessTokenMetadata)).thenReturn(accessToken);
	when(tokenGenerator.generate(refreshTokenCreationStrategy, refreshTokenMetadata)).thenReturn(refreshToken);

	final JwtPayload result = jwtPayloadGenerator.generate(accessTokenMetadata, refreshTokenMetadata);

	assertSoftly(softly -> {
	  softly.assertThat(result.getAccessToken()).isEqualTo(accessToken);
	  softly.assertThat(result.getRefreshToken()).isEqualTo(refreshToken);
	});

  }

  @Test
  @DisplayName("Cookie 배열과 AccessTokenCookieName과 RefreshTokenCookieName을 넣으면 JwtPayload를 반환한다")
  void returnJwtPayloadWhenCookieArrayAndCookieNames () {
	final Cookie[] cookies = FixtureCookie.createCookies(accessToken.getValue(), refreshToken.getValue());

	when(tokenCookieHandler.getAccessTokenFromCookies(any(), anyString())).thenReturn(accessToken);
	when(tokenCookieHandler.getRefreshTokenFromCookies(any(), anyString())).thenReturn(refreshToken);

	final JwtPayload result = jwtPayloadGenerator.generate(cookies, FixtureCookie.ACCESS_TOKEN_COOKIE_NAME,
		FixtureCookie.REFRESH_TOKEN_COOKIE_NAME);

	assertSoftly(softly -> {
	  softly.assertThat(result.getAccessToken()).isEqualTo(accessToken);
	  softly.assertThat(result.getRefreshToken()).isEqualTo(refreshToken);
	});
  }

}
