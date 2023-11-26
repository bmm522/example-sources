package com.example.jwtsecurity.security.cookie;

import jakarta.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class CookieParser {



    public static String getValue(final Cookie[] cookies, final String name) {

        if (Objects.isNull(cookies)) {
            return null;
        }

        return Arrays.stream(cookies)
            .filter(cookie -> name.equals(cookie.getName()))
            .findFirst()
            .map(Cookie::getValue)
            .orElse(null);
    }

}
