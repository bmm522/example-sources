package com.example.jwtsecurity.auth.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Auth0TokenIssuer implements TokenIssuer {

	@Override
	public Token makeToken (final TokenMetadata tokenMetadata) {
		return Token.of(makeTokenString(tokenMetadata));
	}

	private String makeTokenString (TokenMetadata tokenMetadata) {
		Builder builder = JWT.create()
		  .withSubject(tokenMetadata.subject())
		  .withExpiresAt(getExpiresAt(tokenMetadata));
        
        addClaimIfPresent(builder, tokenMetadata);

		return builder.sign(Algorithm.HMAC256(tokenMetadata.secret()));
	}

	private Date getExpiresAt (TokenMetadata tokenMetadata) {
		return tokenMetadata.getExpiresAtOfDateType(LocalDateTime.now());
	}

	private void addClaimIfPresent (Builder builder, TokenMetadata tokenMetadata) {
		if (isExistClaim(tokenMetadata)) {
			builder.withClaim(tokenMetadata.claim(), tokenMetadata.userKey());
		}

	}

	private boolean isExistClaim (TokenMetadata tokenMetadata) {
		return tokenMetadata.isExistClaim();
	}

}
