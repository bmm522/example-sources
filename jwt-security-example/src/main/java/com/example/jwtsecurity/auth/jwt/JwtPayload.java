package com.example.jwtsecurity.auth.jwt;

public class JwtPayload {

        private String accessToken;
        private String refreshToken;

        private JwtPayload(final String accessToken, final String refreshToken) {
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
        }

        public static JwtPayload of(String accessToken, String refreshToken) {
                return new JwtPayload(accessToken, refreshToken);
        }

        public String getAccessToken() {

                return accessToken;
        }

        public String getRefreshToken() {

                return refreshToken;
        }

}
