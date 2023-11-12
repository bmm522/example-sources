package com.example.jwtsecurity.auth.jwt.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class Auth0TokenIssuerTest extends TokenTestFixture {

  private Auth0TokenIssuer auth0TokenIssuer;

  @BeforeEach
  void setUp() {
    auth0TokenIssuer = new Auth0TokenIssuer();
  }

  @Nested
  class TokenMetadata의_정보가 {

    @Test
    void 정상적이면_해당_정보를_가진_토큰이_반환된다() {
      TokenMetadata tokenMetadata = TokenMetadata.of(userKey, secret, subject, expirationTime,
        claimName);

      Token result = auth0TokenIssuer.makeToken(tokenMetadata);
      DecodedJWT actual = getDecodedJWTFrom(result);

      assertSoftly(softly -> {
        softly.assertThat(actual.getSubject()).isEqualTo(subject);
        softly.assertThat(actual.getClaim(claimName).toString())
          .isEqualTo("\"" + userKey + "\"");
      });
    }


  }



  @Nested
  class TokenMetadata에_claim이 {

    @Test
    void 있을땐_claim이_존재하는_JWT_토큰으로_반환() {
      TokenMetadata tokenMetadataWithClaim = createTokenMetadataOfSecretWithClaim(
        secret, claimName);
      Token result = auth0TokenIssuer.makeToken(tokenMetadataWithClaim);
      Claim actual = getDecodedJWTFrom(result).getClaim(claimName);

      assertThat(actual).isNotNull();
    }

    @Test
    void 없을땐_claim이_존재하지_않는_JWT_토큰으로_반환() {
      TokenMetadata tokenMetadataWithOutClaim = createTokenMetadataOfSecretWithOutClaim(secret);
      Token result = auth0TokenIssuer.makeToken(tokenMetadataWithOutClaim);
      Claim actual = getDecodedJWTFrom(result).getClaim(claimName);

      assertThat(actual.isNull()).isTrue();
    }

  }

  private DecodedJWT getDecodedJWTFrom(Token token) {

    return JWT.require(Algorithm.HMAC256(secret)).build()
      .verify(token.getToken());
  }

}
