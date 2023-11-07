package com.example.jwtsecurity.auth;

public interface AuthenticationStrategy<T>{


        T generatePayload(AuthenticationAble authenticationAble);

        void validate();

}
