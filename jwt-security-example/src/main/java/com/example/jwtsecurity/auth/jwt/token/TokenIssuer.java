package com.example.jwtsecurity.auth.jwt.token;

import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenMetadata;
public interface TokenIssuer {

        Token makeToken(TokenMetadata tokenMetadata);

}
