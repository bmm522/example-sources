package com.example.jwtsecurity.auth.jwt.token;

public class PrefixWrapper {

    public static Token of(final String prefix, final Token token) {
        token.wrapPrefix(prefix);
        return token;
    }
}
