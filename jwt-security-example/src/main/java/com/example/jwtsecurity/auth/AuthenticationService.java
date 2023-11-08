package com.example.jwtsecurity.auth;

public interface AuthenticationService<T>{


        T generatePayload(AuthenticationAble authenticationAble);

        void validate();

}
