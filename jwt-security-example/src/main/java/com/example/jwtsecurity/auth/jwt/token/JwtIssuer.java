package com.example.jwtsecurity.auth.jwt.token;

public interface JwtIssuer {

	Token execute(TokenMetadata tokenMetadata);
}
