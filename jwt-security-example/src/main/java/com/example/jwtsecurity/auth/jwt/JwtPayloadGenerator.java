package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.jwt.token.AccessTokenCreationStrategy;
import com.example.jwtsecurity.auth.jwt.token.RefreshTokenCreationStrategy;
import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenGenerator;
import com.example.jwtsecurity.auth.jwt.token.TokenMetadata;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtPayloadGenerator  {

    private final TokenGenerator tokenGenerator;

    private final AccessTokenCreationStrategy accessTokenCreationStrategy;

    private final RefreshTokenCreationStrategy refreshTokenCreationStrategy;

    public JwtPayload generate(TokenMetadata accessTokenMetadata,
        TokenMetadata refreshTokenMetadata) {

        Token accessToken = tokenGenerator.generate(
            accessTokenCreationStrategy, accessTokenMetadata);

        Token refreshToken = tokenGenerator.generate(
            refreshTokenCreationStrategy, refreshTokenMetadata);

        return JwtPayload.of(accessToken, refreshToken);
    }

}
