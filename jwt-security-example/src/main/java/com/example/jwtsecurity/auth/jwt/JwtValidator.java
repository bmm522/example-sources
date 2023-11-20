package com.example.jwtsecurity.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.jwtsecurity.auth.jwt.token.Token;
import com.example.jwtsecurity.auth.jwt.token.TokenDecoder;
import com.example.jwtsecurity.auth.jwt.token.TokenIssuer;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JwtValidator {

    private final TokenDecoder tokenDecoder;


    public void validate() {
        System.out.println("jwt validate");
    }

    public boolean isExpiredToken(final Token token, final String secretKey) {
        try {
            tokenDecoder.decodedJWT(token, secretKey);
            return false;
        } catch (TokenExpiredException e) {
            return true;
        }
    }

}
