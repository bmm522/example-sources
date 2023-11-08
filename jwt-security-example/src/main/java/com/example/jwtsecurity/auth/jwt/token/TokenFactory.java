package com.example.jwtsecurity.auth.jwt.token;


import com.example.jwtsecurity.auth.AuthenticationAble;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenFactory {

        private final TokenProperties tokenProperties;

        public Token generateAccessToken(AuthenticationAble authenticationAble) {
                try {
                        return Token.generateAccessToken(authenticationAble.getUserKey(),
                            tokenProperties);
                } catch (Exception e) {
                        throw new RuntimeException("Access Token Generate Error");
                }
        }

        public Token generateRefreshToken(AuthenticationAble authenticationAble) {
                try {
                        return Token.generateRefreshToken(authenticationAble.getUserKey(),
                            tokenProperties);
                } catch (Exception e) {
                        throw new RuntimeException("Access Token Generate Error");
                }
        }


}
