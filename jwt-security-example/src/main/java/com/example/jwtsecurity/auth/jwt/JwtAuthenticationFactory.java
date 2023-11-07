package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.AuthenticationAble;
import com.example.jwtsecurity.auth.AuthenticationFactory;
import com.example.jwtsecurity.auth.jwt.token.TokenProvider;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class JwtAuthenticationFactory implements AuthenticationFactory<JwtPayload> {


        private final TokenProvider tokenProvider;

        @Override
        public JwtPayload generatePayload(AuthenticationAble authenticationAble) {
                String accessToken = tokenProvider.generateAccessToken(authenticationAble);
                String refreshToken = tokenProvider.generateRefreshToken(authenticationAble);
                return JwtPayload.of(accessToken, refreshToken);
        }


}
