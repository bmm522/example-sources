package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenMetadata;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtService {

    private final JwtPayloadGenerator payloadGenerator;

    private final JwtValidator jwtValidator;

    private final JwtTokenProperties jwtTokenProperties;

    public JwtPayload generatePayload(JwtAuthenticationAble jwtAuthenticationAble) {
        TokenMetadata accessTokenMetadata = TokenMetadata.createAccessTokenMetadata(
            jwtAuthenticationAble, jwtTokenProperties);
        TokenMetadata refreshTokenMetadata = TokenMetadata.createRefreshTokenMetadata(
            jwtAuthenticationAble, jwtTokenProperties);
        return payloadGenerator.generate(accessTokenMetadata, refreshTokenMetadata);
    }

    public void validate() {
    }

    public boolean isExpiredAccessToken(final Token accessToken) {
        return jwtValidator.isExpiredAccessToken(accessToken,
            jwtTokenProperties.getSecretKey());
    }

    public boolean isExpiredRefreshToken(final Token refreshToken) {
        return jwtValidator.isExpiredRefreshToken(refreshToken,
            jwtTokenProperties.getSecretKey());
    }


}
