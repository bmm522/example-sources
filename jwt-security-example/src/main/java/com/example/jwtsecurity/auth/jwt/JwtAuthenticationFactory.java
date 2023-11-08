package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.AuthenticationAble;
import com.example.jwtsecurity.auth.AuthenticationFactory;
import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class JwtAuthenticationFactory implements AuthenticationFactory<JwtPayload> {


        private final TokenFactory tokenFactory;

        @Override
        public JwtPayload generatePayload(AuthenticationAble authenticationAble) {
                Token accessToken = tokenFactory.generateAccessToken(authenticationAble);
                Token refreshToken = tokenFactory.generateRefreshToken(authenticationAble);
                return JwtPayload.of(accessToken.getToken(), refreshToken.getToken());
        }


}
