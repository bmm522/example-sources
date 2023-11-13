package com.example.jwtsecurity.auth;

import com.example.jwtsecurity.auth.jwt.token.Token;
public interface AuthenticationValidator {

    void validate();


    boolean isExpiredAccessToken(Token accessToken, String secretKey);

    boolean isExpiredRefreshToken(Token refreshToken, String secretKey);

}
