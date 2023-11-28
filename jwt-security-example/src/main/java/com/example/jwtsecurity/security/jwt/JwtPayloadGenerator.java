package com.example.jwtsecurity.security.jwt;

import com.example.jwtsecurity.security.jwt.token.AccessTokenCreationStrategy;
import com.example.jwtsecurity.security.jwt.token.RefreshTokenCreationStrategy;
import com.example.jwtsecurity.security.jwt.token.Token;
import com.example.jwtsecurity.security.jwt.token.TokenCookieHandler;
import com.example.jwtsecurity.security.jwt.token.TokenGenerator;
import com.example.jwtsecurity.security.jwt.token.TokenMetadata;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtPayloadGenerator {

    private final TokenGenerator tokenGenerator;

    private final AccessTokenCreationStrategy accessTokenCreationStrategy;

    private final RefreshTokenCreationStrategy refreshTokenCreationStrategy;

    private final TokenCookieHandler tokenCookieHandler;

    public JwtPayload generate(TokenMetadata accessTokenMetadata,
        TokenMetadata refreshTokenMetadata) {

        Token accessToken = tokenGenerator.generate(
            accessTokenCreationStrategy, accessTokenMetadata);

        Token refreshToken = tokenGenerator.generate(
            refreshTokenCreationStrategy, refreshTokenMetadata);

        return JwtPayload.of(accessToken, refreshToken);
    }

    public JwtPayload generate(Cookie[] cookies, String accessTokenCookieName,
        String refreshTokenCookieName) {
        Token accessToken = tokenCookieHandler.getAccessTokenFromCookies(cookies, accessTokenCookieName);
        Token refreshToken = tokenCookieHandler.getRefreshTokenFromCookies(cookies, refreshTokenCookieName);
        return JwtPayload.of(accessToken, refreshToken);
    }


}
