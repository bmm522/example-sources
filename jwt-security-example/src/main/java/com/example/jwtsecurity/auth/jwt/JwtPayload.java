package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.Payload;

public class JwtPayload implements Payload {

        private String accessToken;
        private String refreshToken;

        private JwtPayload(final String accessToken, final String refreshToken) {
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
        }

        public static JwtPayload of(String accessToken, String refreshToken) {
                return new JwtPayload(accessToken, refreshToken);
        }

        @Override
        public String getAccessToken() {

                return accessToken;
        }

        @Override
        public String getRefreshToken() {

                return refreshToken;
        }

}
