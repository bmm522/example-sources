package com.example.jwtsecurity.util;

import jakarta.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class CookieParser {

    public static Optional<String> getValue(final Cookie[] cookies, final String name) {

        if (Objects.isNull(cookies)) {
            return Optional.empty();
        }

        return Arrays.stream(cookies)
            .filter(cookie -> name.equals(cookie.getName()))
            .findFirst()
            .map(Cookie::getValue);
    }

}
