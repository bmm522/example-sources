package com.example.jwtsecurity.security.jwt.token;

public interface TokenIssuer {

  String issue (TokenMetadata tokenMetadata);

}
