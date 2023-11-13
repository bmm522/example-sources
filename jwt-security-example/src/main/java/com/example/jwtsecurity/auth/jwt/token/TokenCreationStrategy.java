package com.example.jwtsecurity.auth.jwt.token;

import com.example.jwtsecurity.auth.AuthenticationAble;

public interface TokenCreationStrategy {

        Token execute(TokenMetadata tokenMetadata);

}
