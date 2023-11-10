package com.example.jwtsecurity.auth.jwt;

import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.AuthenticationValidator;


@Component
public class JwtAuthenticationValidator implements AuthenticationValidator {

        @Override
        public void validate() {
                System.out.println("jwt validate");
        }

}
