package com.example.jwtsecurity.util;

import jakarta.servlet.http.Cookie;
public class CookieIssuer {

    public static Cookie execute(String cookieName, String tokenValue, int expiry) {
        Cookie cookie = new Cookie(cookieName, tokenValue);
        cookie.setPath("/");
        cookie.setMaxAge(expiry);
        cookie.setSecure(true);
        return cookie;
    }
}
