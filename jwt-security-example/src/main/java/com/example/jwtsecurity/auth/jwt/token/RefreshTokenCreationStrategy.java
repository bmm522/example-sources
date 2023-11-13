package com.example.jwtsecurity.auth.jwt.token;

import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.AuthenticationAble;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RefreshTokenCreationStrategy implements TokenCreationStrategy {


  private final TokenIssuer tokenIssuer;

  @Override
  public Token execute(TokenMetadata tokenMetadata) {
    Token token = tokenIssuer.makeToken(tokenMetadata);
    token.setPrefix(tokenMetadata.prefix());

    return token;
  }

}
