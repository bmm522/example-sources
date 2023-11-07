package com.example.jwtsecurity.auth.jwt.token;

import com.example.jwtsecurity.auth.AuthenticationAble;

public interface TokenStrategy {
        String generateToken(AuthenticationAble authenticationAble);
}
