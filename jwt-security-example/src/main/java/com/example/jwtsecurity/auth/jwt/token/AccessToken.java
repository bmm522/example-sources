package com.example.jwtsecurity.auth.jwt.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class AccessToken implements Token{

    private String token;

    public static Token of(final String token) {
        return new AccessToken(token);
    }

    @Override
    public void wrapPrefix(final String prefix) {
        this.token = prefix + token;
    }

}
