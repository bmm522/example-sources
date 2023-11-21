package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenGenerator;
import com.example.jwtsecurity.auth.jwt.token.TokenMetadata;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtService {

    private final TokenGenerator tokenGenerator;

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

    public JwtPayload reIssueIfTokenExpired(JwtAuthenticationAble jwtAuthenticationAble,
        final JwtPayload jwtPayload) {
        if (isExpiredToken(jwtPayload)) {
            jwtPayload = this.generatePayload()
        }
        return jwtPayload;
    }

    private boolean isExpiredToken(final JwtPayload jwtPayload) {
        String secretKey =  jwtTokenProperties.getSecretKey();
        return jwtValidator.isExpiredToken(jwtPayload.getAccessToken(), secretKey)
            || jwtValidator.isExpiredToken(jwtPayload.getRefreshToken(),secretKey);
    }

    public boolean isExpiredRefreshToken(final Token refreshToken) {
        return jwtValidator.isExpiredToken(refreshToken,
            jwtTokenProperties.getSecretKey());
    }


}
