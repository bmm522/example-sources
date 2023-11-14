package com.example.jwtsecurity.auth.jwt.token;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


public interface Token {

        void wrapPrefix(final String prefix);


}
