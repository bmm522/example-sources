package com.example.jwtsecurity.auth.jwt.token;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Token {

        private String token;

        public static Token from(final String token) {
                return new Token(token);
        }



}
