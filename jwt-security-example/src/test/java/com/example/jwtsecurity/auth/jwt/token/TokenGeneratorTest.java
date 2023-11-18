package com.example.jwtsecurity.auth.jwt.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.jwtsecurity.auth.jwt.fixture.FixtureToken;
import com.example.jwtsecurity.auth.jwt.fixture.FixtureTokenMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TokenGeneratorTest {

    @Mock
    AccessTokenCreationStrategy accessTokenCreationStrategy;

    @Mock
    RefreshTokenCreationStrategy refreshTokenCreationStrategy;

    TokenGenerator tokenGenerator;

    @BeforeEach
    void setUp() {
        tokenGenerator = new TokenGeneratorImpl();
    }

    @Test
    @DisplayName("AccessToken 생성 전략 객체와 그에 맞는 메타데이터를 넣으면 prefix가 붙어서 AccessToken 토큰이 발급된다")
    void generatedAccessTokenTest() {
        TokenMetadata tokenMetadata = FixtureTokenMetadata.createTokenMetadataWithPrefix("Bearer ");
        when(accessTokenCreationStrategy.execute(any())).thenReturn(FixtureToken.createAccessToken());
        Token actual = tokenGenerator.generate(accessTokenCreationStrategy, tokenMetadata);

        assertSoftly(softly -> {
            softly.assertThat(actual.getValue().startsWith("Bearer ")).isTrue();
            softly.assertThat(actual.getClass()).isEqualTo(AccessToken.class);
        });


    }

    @Test
    @DisplayName("RefreshToken 생성 전략 객체와 그에 맞는 메타데이터를 넣으면 prefix가 붙어서 RefreshToken 토큰이 발급된다")
    void generatedRefreshTokenTest() {
        TokenMetadata tokenMetadata = FixtureTokenMetadata.createTokenMetadataWithPrefix(
            "RefreshToken ");
        when(refreshTokenCreationStrategy.execute(any())).thenReturn(
            FixtureToken.createRefreshToken());
        Token actual = tokenGenerator.generate(refreshTokenCreationStrategy, tokenMetadata);

        assertSoftly(softly -> {
            softly.assertThat(actual.getValue().startsWith("RefreshToken ")).isTrue();
            softly.assertThat(actual.getClass()).isEqualTo(RefreshToken.class);
        });

    }


}
