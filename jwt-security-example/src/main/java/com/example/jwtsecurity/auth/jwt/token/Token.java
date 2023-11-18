package com.example.jwtsecurity.auth.jwt.token;


public interface Token {

        void wrapPrefix(final String prefix);

        String getValue();

        boolean isStartsWith(final String prefix);

}
