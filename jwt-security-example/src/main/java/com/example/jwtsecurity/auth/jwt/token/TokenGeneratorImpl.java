package com.example.jwtsecurity.auth.jwt.token;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TokenGeneratorImpl implements TokenGenerator {

        @Override
        public Token generate(TokenCreationStrategy tokenCreationStrategy,
            TokenMetadata tokenMetadata) {
                Token token =  tokenCreationStrategy.execute(tokenMetadata);
                return PrefixWrapper.of(tokenMetadata.prefix(), token);
        }

}
