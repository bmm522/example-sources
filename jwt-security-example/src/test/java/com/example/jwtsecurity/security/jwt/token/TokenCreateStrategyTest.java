package com.example.jwtsecurity.security.jwt.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.jwtsecurity.fixture.FixtureTokenMetadata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @see TokenCreationStrategy
 */
@ExtendWith(MockitoExtension.class)
public class TokenCreateStrategyTest {

  @Mock
  TokenIssuer tokenIssuer;

  @InjectMocks
  AccessTokenCreationStrategy accessTokenCreationStrategy;

  @InjectMocks
  RefreshTokenCreationStrategy refreshTokenCreationStrategy;

  @Test
  @DisplayName("Access 토큰 생성 전략을 실행하면 AccessToken 토큰을 반환한다.")
  void returnAccessTokenWhenAccessTokenCreationStrategyActive () {
	final Token result = accessTokenCreationStrategy.execute(FixtureTokenMetadata.createAccessTokenMetadata());

	assertThat(result).isInstanceOf(AccessToken.class);
  }

  @Test
  @DisplayName("Refresh 토큰 생성 전략을 실행하면 RefreshToken 토큰을 반환한다.")
  void returnRefreshTokenWhenRefreshTokenCreationStrategyActive () {
	final Token result = refreshTokenCreationStrategy.execute(FixtureTokenMetadata.createRefreshTokenMetadata());

	assertThat(result).isInstanceOf(RefreshToken.class);
  }

}
