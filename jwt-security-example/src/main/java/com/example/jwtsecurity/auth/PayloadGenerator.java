package com.example.jwtsecurity.auth;

import com.example.jwtsecurity.auth.jwt.token.TokenMetadata;
import com.example.jwtsecurity.auth.jwt.token.TokenMetadatas;
public interface PayloadGenerator<T extends Payload> {

    T generate(TokenMetadata accessTokenMetadata, TokenMetadata refreshTokenMetadata);

}
