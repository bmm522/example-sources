package com.example.jwtsecurity.auth.jwt.token;

import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.AuthenticationAble;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TokenGeneratorImpl implements TokenGenerator {

        @Override
        public Token generate(TokenCreationStrategy tokenCreationStrategy,
            TokenMetadata tokenMetadata) {

                return tokenCreationStrategy.execute(tokenMetadata);
        }

}
