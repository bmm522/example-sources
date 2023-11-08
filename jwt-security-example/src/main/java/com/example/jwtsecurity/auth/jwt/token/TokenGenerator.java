package com.example.jwtsecurity.auth.jwt.token;

import com.example.jwtsecurity.auth.AuthenticationAble;

public interface TokenGenerator {

        Token generateAccessToken(AuthenticationAble authenticationAble);

        Token generateRefreshToken(AuthenticationAble authenticationAble);
}
