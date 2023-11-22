package com.example.jwtsecurity.common;
public enum YN {

    Y("Y"), N("N");

    private final String value;
    YN(final String value) {
        this.value = value;
    }

    public static boolean isY(final String value) {
        return Y.value.equals(value);
    }

    public static boolean isN(final String value) {
        return N.value.equals(value);
    }
}
