package com.example.jwtsecurity.auth.jwt.token;

import com.example.jwtsecurity.auth.AuthenticationAble;

public interface TokenGenerator {

        Token generate(TokenCreationStrategy tokenCreationStrategy,
            AuthenticationAble authenticationAble);

}
