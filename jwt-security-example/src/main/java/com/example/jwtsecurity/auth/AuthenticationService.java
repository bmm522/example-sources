package com.example.jwtsecurity.auth;




import com.example.jwtsecurity.auth.jwt.token.Token;
public interface AuthenticationService<T> {

    T generatePayload(AuthenticationAble authenticationAble);

    void validate();

    boolean isExpiredAccessToken(Token accessToken);

    boolean isExpiredRefreshToken(Token refreshToken);

}
