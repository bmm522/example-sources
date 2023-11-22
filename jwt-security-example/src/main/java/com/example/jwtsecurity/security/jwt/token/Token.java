package com.example.jwtsecurity.security.jwt.token;

public interface Token {

        void wrapPrefix(final String prefix);

        String getValue();

        boolean isStartsWith(final String prefix);

        boolean isNotStartsWith(final String prefix);

}
