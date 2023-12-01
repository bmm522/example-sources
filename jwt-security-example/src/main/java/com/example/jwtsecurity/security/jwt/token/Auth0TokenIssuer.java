package com.example.jwtsecurity.security.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Auth0TokenIssuer implements TokenIssuer {

  @Override
  public String issue (final TokenMetadata tokenMetadata) {
	return makeTokenString(tokenMetadata);
  }

  private String makeTokenString (final TokenMetadata tokenMetadata) {
	final Builder builder = JWT.create()
		.withSubject(tokenMetadata.subject())
		.withExpiresAt(getExpiresAt(tokenMetadata));

	addClaimIfPresent(builder, tokenMetadata);

	return builder.sign(Algorithm.HMAC256(tokenMetadata.secret()));
  }

  private Date getExpiresAt (final TokenMetadata tokenMetadata) {
	return tokenMetadata.getExpiresAtOfDateType(LocalDateTime.now());
  }

  private void addClaimIfPresent (final Builder builder, final TokenMetadata tokenMetadata) {
	if (isExistClaim(tokenMetadata)) {
	  builder.withClaim(tokenMetadata.claim(), tokenMetadata.userId());
	}
  }

  private boolean isExistClaim (final TokenMetadata tokenMetadata) {
	return tokenMetadata.isExistClaim();
  }

}
