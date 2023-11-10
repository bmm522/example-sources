package com.example.jwtsecurity.auth;

public interface AuthenticationGenerator<T extends Payload> {

          T  generate (AuthenticationAble authenticationAble);

}
