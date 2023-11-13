package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.Payload;
import com.example.jwtsecurity.auth.jwt.token.Token;

public class JwtPayload implements Payload {

        private Token accessToken;
        private Token refreshToken;

        private JwtPayload(final Token accessToken, final Token refreshToken) {
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
        }

        public static JwtPayload of(Token accessToken, Token refreshToken) {
                return new JwtPayload(accessToken, refreshToken);
        }

        @Override
        public Token getAccessToken() {

                return accessToken;
        }

        @Override
        public Token getRefreshToken() {

                return refreshToken;
        }

}
