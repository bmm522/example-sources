package com.example.jwtsecurity.security.jwt;

import com.example.jwtsecurity.security.jwt.token.AccessTokenCreationStrategy;
import com.example.jwtsecurity.security.jwt.token.RefreshTokenCreationStrategy;
import com.example.jwtsecurity.security.jwt.token.Token;
import com.example.jwtsecurity.security.jwt.token.TokenGenerator;
import com.example.jwtsecurity.security.jwt.token.TokenMetadata;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtService {

    private final TokenGenerator tokenGenerator;

    private final AccessTokenCreationStrategy accessTokenCreationStrategy;

    private final RefreshTokenCreationStrategy refreshTokenCreationStrategy;

    private final JwtPayloadGenerator payloadGenerator;

    private final JwtValidator jwtValidator;

    private final JwtTokenProperties jwtTokenProperties;



    public JwtPayload generatePayload(AuthenticationAble authenticationAble) {
        Token accessToken = generateAccessToken(authenticationAble);
        Token refreshToken = generateRefreshToken(authenticationAble);

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

    public void checkValidAccessToken(final Token accessToken) {
        jwtValidator.validateCheckPrefix(accessToken, jwtTokenProperties.getAccessTokenPrefix());
    }

    public void checkValidRefreshToken(final Token refreshToken) {
        jwtValidator.validateCheckPrefix(refreshToken, jwtTokenProperties.getRefreshTokenPrefix());
    }

//    public JwtPayload reIssueIfTokenExpired(AuthenticationAble authenticationAble,
//        final JwtPayload jwtPayload) {
//        if (isTokenExpired(jwtPayload)) {
//            jwtPayload = this.generatePayload()
//        }
//        return jwtPayload;
//    }

    private boolean isExpiredToken(final JwtPayload jwtPayload) {
        String secretKey =  jwtTokenProperties.getSecretKey();
        return jwtValidator.isTokenExpired(jwtPayload.getAccessToken(), secretKey)
            || jwtValidator.isTokenExpired(jwtPayload.getRefreshToken(),secretKey);
    }

    public boolean isExpiredRefreshToken(final Token refreshToken) {
        return jwtValidator.isTokenExpired(refreshToken,
            jwtTokenProperties.getSecretKey());
    }


}