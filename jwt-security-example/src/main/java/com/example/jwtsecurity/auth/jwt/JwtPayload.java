package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.jwt.token.Token;

public class JwtPayload {

        private Token accessToken;
        private Token refreshToken;

        private JwtPayload(final Token accessToken, final Token refreshToken) {
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
        }

        public static JwtPayload of(Token accessToken, Token refreshToken) {
                return new JwtPayload(accessToken, refreshToken);
        }
        public Token getAccessToken() {

                return accessToken;
        }

        public Token getRefreshToken() {

                return refreshToken;
        }

}
