package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JwtValidator {

    private final TokenDecoder tokenDecoder;

    public boolean isTokenExpired(final Token token, final String secretKey) {
       return tokenDecoder.isTokenExpired(token, secretKey);
    }

}
