package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenMetadata;
import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.AuthenticationAble;
import com.example.jwtsecurity.auth.PayloadGenerator;
import com.example.jwtsecurity.auth.AuthenticationService;
import com.example.jwtsecurity.auth.AuthenticationValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationService implements AuthenticationService<JwtPayload> {

    private final PayloadGenerator<JwtPayload> payloadGenerator;

    private final AuthenticationValidator authenticationValidator;

    private final JwtTokenProperties jwtTokenProperties;

    @Override
    public JwtPayload generatePayload(AuthenticationAble authenticationAble) {
        TokenMetadata accessTokenMetadata = TokenMetadata.createAccessTokenMetadata(
            authenticationAble, jwtTokenProperties);
        TokenMetadata refreshTokenMetadata = TokenMetadata.createRefreshTokenMetadata(
            authenticationAble, jwtTokenProperties);
        return payloadGenerator.generate(accessTokenMetadata, refreshTokenMetadata);
    }

    public void validate() {
    }
    @Override
    public boolean isExpiredAccessToken(final Token accessToken) {
        return authenticationValidator.isExpiredAccessToken(accessToken,
            jwtTokenProperties.getSecretKey());
    }
    @Override
    public boolean isExpiredRefreshToken(final Token refreshToken) {
        return authenticationValidator.isExpiredRefreshToken(refreshToken,
            jwtTokenProperties.getSecretKey());
    }


}
