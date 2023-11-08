package com.example.jwtsecurity.auth;

public interface AuthenticationProvider<T>{


        T generatePayload(AuthenticationAble authenticationAble);

        void validate();

}
