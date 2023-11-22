package com.example.jwtsecurity.security.jwt.token;
import com.auth0.jwt.interfaces.DecodedJWT;
public interface TokenDecoder {


    boolean isTokenExpired(Token token, String secretKey);

    DecodedJWT decodedJWT(Token token, String secretKey);

}