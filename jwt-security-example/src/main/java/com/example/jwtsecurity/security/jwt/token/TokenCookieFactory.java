package com.example.jwtsecurity.security.jwt.token;


import javax.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class TokenCookieFactory {

    public Cookie of(Token token) {
        if (token instanceof AccessToken) {
            return getCookie(AccessToken.class.getSimpleName(), token.getValue(), 10);
        }

        if (token instanceof RefreshToken) {
            return getCookie(RefreshToken.class.getSimpleName(), token.getValue(), 20);
        }

        throw new RuntimeException("일치하는 Token 타입이 없습니다.");
    }

    private Cookie getCookie(String tokenName, String tokenValue, int expiry) {
        Cookie cookie = new Cookie(tokenName, tokenValue);
        cookie.setPath("/");
        cookie.setMaxAge(expiry);
        cookie.setSecure(true);
        return cookie;
    }

}
