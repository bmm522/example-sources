package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.AuthenticationValidator;


public class JwtAuthenticationValidator implements AuthenticationValidator {

        @Override
        public void validate() {
                System.out.println("jwt validate");
        }

}
