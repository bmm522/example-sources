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
  @DisplayName("TokenMetadata의 정보가")
  class TokenMetadataInformation {

    @Test
    @DisplayName("정상적이면 해당 정보를 가진 토큰이 반환된다")
    void shouldReturnTokenWithGivenInformationWhenMetadataIsValid() {
      TokenMetadata tokenMetadata = TokenMetadata.of(userKey, secret, subject, expirationTime,
        prefix,claimName);

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
  @DisplayName("TokenMetadata에 claim이")
  class TokenMetadataClaim {

    @Test
    @DisplayName("있을땐 claim이 존재하는 JWT 토큰으로 반환")
    void shouldReturnJwtTokenWithClaimWhenClaimExists() {
      TokenMetadata tokenMetadataWithClaim = createTokenMetadataOfSecretWithClaim(
        secret, claimName);
      Token result = auth0TokenIssuer.makeToken(tokenMetadataWithClaim);
      Claim actual = getDecodedJWTFrom(result).getClaim(claimName);

      assertThat(actual).isNotNull();
    }

    @Test
    @DisplayName("없을땐 claim이 존재하지 않는 JWT 토큰으로 반환")
    void shouldReturnJwtTokenWithoutClaimWhenClaimDoesNotExist() {
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
