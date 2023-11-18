package com.example.jwtsecurity.auth.jwt;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

import com.example.jwtsecurity.auth.jwt.token.AccessTokenCreationStrategy;
import com.example.jwtsecurity.auth.jwt.token.RefreshTokenCreationStrategy;
import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenGenerator;
import com.example.jwtsecurity.auth.jwt.token.TokenMetadata;
import com.example.jwtsecurity.auth.jwt.fixture.FixtureToken;
import com.example.jwtsecurity.auth.jwt.fixture.FixtureTokenMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JwtPayloadGeneratorTest {


    @Mock
    private TokenGenerator tokenGenerator;

    @Mock
    private AccessTokenCreationStrategy accessTokenCreationStrategy;

    @Mock
    private RefreshTokenCreationStrategy refreshTokenCreationStrategy;

    @InjectMocks
    private JwtPayloadGenerator jwtPayloadGenerator;

    TokenMetadata accessTokenMetadata;

    TokenMetadata refreshTokenMetadata;

    Token accessToken;

    Token refreshToken;
    @BeforeEach
    void setUp() {
        accessTokenMetadata = FixtureTokenMetadata.createAccessTokenMetadata();
        refreshTokenMetadata = FixtureTokenMetadata.createRefreshTokenMetadata();

        accessToken = FixtureToken.createAccessToken();
        refreshToken = FixtureToken.createRefreshToken();
    }

    @Test
    @DisplayName("TokenMetadata를 넣으면 JwtPayload를 반환한다 ")
    void returnTokenMetadataWhenInputJwtPayload() {
        when(tokenGenerator.generate(accessTokenCreationStrategy, accessTokenMetadata)).thenReturn(accessToken);
        when(tokenGenerator.generate(refreshTokenCreationStrategy, refreshTokenMetadata)).thenReturn(refreshToken);

        JwtPayload result = jwtPayloadGenerator.generate(accessTokenMetadata, refreshTokenMetadata);

        assertSoftly(softly -> {
            softly.assertThat(result.getAccessToken()).isEqualTo(accessToken);
            softly.assertThat(result.getRefreshToken()).isEqualTo(refreshToken);
        });

    }



}
