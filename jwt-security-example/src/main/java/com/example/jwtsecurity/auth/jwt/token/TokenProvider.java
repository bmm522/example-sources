package com.example.jwtsecurity.auth.jwt.token;


import com.example.jwtsecurity.auth.AuthenticationAble;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenProvider {

        private final TokenStrategy accessTokenStrategy;

        private final TokenStrategy refreshTokenStrategy;

        public String generateAccessToken(AuthenticationAble authenticationAble) {

                try {
                        return accessTokenStrategy.generateToken(authenticationAble);
                } catch (Exception e) {
                        throw new RuntimeException("Access Token Generate Error");
                }

        }

        public String generateRefreshToken(AuthenticationAble authenticationAble) {

                try {
                        return refreshTokenStrategy.generateToken(authenticationAble);
                } catch (Exception e) {
                        throw new RuntimeException("Refresh Token Generate Error");
                }

        }

}
