package com.example.jwtsecurity.auth;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationProvider<T> {

        private final AuthenticationStrategy<T> authenticationStrategy;


        public T generatePayload(AuthenticationAble authenticationAble) {
                return authenticationStrategy.generatePayload(authenticationAble);
        }


        public void validate() {
                authenticationStrategy.validate();
        }

}
