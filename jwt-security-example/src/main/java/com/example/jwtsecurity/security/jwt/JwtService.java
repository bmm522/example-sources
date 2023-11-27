package com.example.jwtsecurity.security.jwt;

import com.example.jwtsecurity.security.AuthenticationAble;
import com.example.jwtsecurity.security.cookie.CookieParser;
import com.example.jwtsecurity.security.jwt.token.AccessToken;
import com.example.jwtsecurity.security.jwt.token.AccessTokenCreationStrategy;
import com.example.jwtsecurity.security.jwt.token.RefreshTokenCreationStrategy;
import com.example.jwtsecurity.security.jwt.token.Token;
import com.example.jwtsecurity.security.jwt.token.TokenCookieFactory;
import com.example.jwtsecurity.security.jwt.token.TokenGenerator;
import com.example.jwtsecurity.security.jwt.token.TokenMetadata;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtService {

    private final TokenGenerator tokenGenerator;

    private final AccessTokenCreationStrategy accessTokenCreationStrategy;

    private final RefreshTokenCreationStrategy refreshTokenCreationStrategy;

    private final JwtValidator jwtValidator;

    private final JwtTokenProperties jwtTokenProperties;

    private final TokenCookieFactory tokenCookieFactory;



    public JwtPayload generatePayload(AuthenticationAble authenticationAble) {
        Token accessToken = generateAccessToken(authenticationAble);
        Token refreshToken = generateRefreshToken(authenticationAble);
        return JwtPayload.of(accessToken, refreshToken);
    }

    public JwtPayload generatePayload(String accessTokenValue, String refreshTokenValue) {
        Token accessToken = accessTokenCreationStrategy.execute(accessTokenValue);
        Token refreshToken = refreshTokenCreationStrategy.execute(refreshTokenValue);
        return JwtPayload.of(accessToken, refreshToken);
    }

    public Token generateAccessToken(AuthenticationAble authenticationAble) {
        TokenMetadata accessTokenMetadata = getAccessTokenMetadata(authenticationAble);
        return  tokenGenerator.generate(accessTokenCreationStrategy, accessTokenMetadata);
    }

    public Token generateRefreshToken(AuthenticationAble authenticationAble) {
        TokenMetadata refreshTokenMetadata = getRefreshTokenMetadata(authenticationAble);
        return tokenGenerator.generate(refreshTokenCreationStrategy, refreshTokenMetadata);
    }

    private TokenMetadata getAccessTokenMetadata(AuthenticationAble authenticationAble) {
        return TokenMetadata.createAccessTokenMetadata(authenticationAble, jwtTokenProperties);
    }

    private TokenMetadata getRefreshTokenMetadata(AuthenticationAble authenticationAble) {
        return TokenMetadata.createRefreshTokenMetadata(authenticationAble, jwtTokenProperties);
    }

}
