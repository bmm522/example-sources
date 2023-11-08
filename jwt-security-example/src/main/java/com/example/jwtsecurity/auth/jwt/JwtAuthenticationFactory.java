package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.AuthenticationAble;
import com.example.jwtsecurity.auth.AuthenticationFactory;
import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenGenerator;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class JwtAuthenticationFactory implements AuthenticationFactory<JwtPayload> {


        private final TokenGenerator tokenGenerator;

        @Override
        public JwtPayload generatePayload(AuthenticationAble authenticationAble) {
                Token accessToken = tokenGenerator.generateAccessToken(authenticationAble);
                Token refreshToken = tokenGenerator.generateRefreshToken(authenticationAble);
                return JwtPayload.of(accessToken.getToken(), refreshToken.getToken());
        }


}
