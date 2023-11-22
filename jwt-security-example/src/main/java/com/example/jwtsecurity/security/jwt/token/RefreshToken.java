package com.example.jwtsecurity.security.jwt.token;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class RefreshToken implements Token {

    private String token;

    public static Token of(final String token) {
        return new RefreshToken(token);
    }

    @Override
    public void wrapPrefix(final String prefix) {
        this.token = prefix + token;
    }

    @Override
    public String getValue() {
        return token.toString();
    }
    @Override
    public boolean isStartsWith(final String prefix) {
        return this.token.startsWith(prefix);
    }
    @Override
    public boolean isNotStartsWith(final String prefix) {
        return !this.token.startsWith(prefix);
    }

}
