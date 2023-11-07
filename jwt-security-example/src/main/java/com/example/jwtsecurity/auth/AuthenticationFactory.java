package com.example.jwtsecurity.auth;

public interface AuthenticationFactory<T> {

        T generatePayload(AuthenticationAble authenticationAble);

}
