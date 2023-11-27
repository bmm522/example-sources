package com.example.jwtsecurity.security.jwt.token;



import java.util.Objects;
public class RefreshToken implements Token {

    private String token;

    private RefreshToken(final String token) {
        this.token = token;
    }
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
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RefreshToken that)) {
            return false;
        }
        return Objects.equals(token, that.token);
    }
    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

}
