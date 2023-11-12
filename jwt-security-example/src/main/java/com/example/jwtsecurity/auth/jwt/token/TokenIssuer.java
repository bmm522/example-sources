package com.example.jwtsecurity.auth.jwt.token;

import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenMetadata;
import java.time.LocalDateTime;
public interface TokenIssuer {

        Token makeToken(TokenMetadata tokenMetadata);

}
