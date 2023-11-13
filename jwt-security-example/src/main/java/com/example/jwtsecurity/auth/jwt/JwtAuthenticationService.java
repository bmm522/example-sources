package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.Payload;
import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenMetadatas;
import com.example.jwtsecurity.auth.jwt.token.TokenProperties;
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

    private final TokenProperties tokenProperties;

    @Override
    public JwtPayload generatePayload(AuthenticationAble authenticationAble) {
        return payloadGenerator.generate(
            TokenMetadatas.of(authenticationAble, tokenProperties));
    }

    public void validate() {
    }
    @Override
    public boolean isExpiredAccessToken(final Token accessToken) {
        return authenticationValidator.isExpiredAccessToken(accessToken,
            tokenProperties.getSecretKey());
    }
    @Override
    public boolean isExpiredRefreshToken(final Token refreshToken) {
        return authenticationValidator.isExpiredRefreshToken(refreshToken,
            tokenProperties.getSecretKey());
    }


}
