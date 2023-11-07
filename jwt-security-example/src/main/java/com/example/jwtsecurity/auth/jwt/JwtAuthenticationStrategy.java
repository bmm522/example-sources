package com.example.jwtsecurity.auth.jwt;


import com.example.jwtsecurity.auth.AuthenticationAble;
import com.example.jwtsecurity.auth.AuthenticationFactory;
import com.example.jwtsecurity.auth.AuthenticationStrategy;
import com.example.jwtsecurity.auth.AuthenticationValidator;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class JwtAuthenticationStrategy implements AuthenticationStrategy<JwtPayload> {

        private final AuthenticationFactory<JwtPayload> authenticationFactory;
        private final AuthenticationValidator authenticationValidator;


        public JwtPayload generatePayload(AuthenticationAble authenticationAble) {
                return authenticationFactory.generatePayload(authenticationAble);
        }

        public void validate() {
                authenticationValidator.validate();
        }

}
