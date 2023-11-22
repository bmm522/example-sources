package com.example.jwtsecurity.security.jwt.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.example.jwtsecurity.security.jwt.fixture.FixtureTokenMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class Auth0TokenIssuerTest {

    private Auth0TokenIssuer auth0TokenIssuer;

    @BeforeEach
    void setUp() {
        auth0TokenIssuer = new Auth0TokenIssuer();
    }


    @Test
    @DisplayName("TokenMetadata의 정보를 넣으면 해당 정보를 가진 토큰이 반환된다")
    void shouldReturnTokenWithGivenInformationWhenMetadataIsValid() {
        TokenMetadata tokenMetadata = FixtureTokenMetadata.createTokenMetadata();

        String result = auth0TokenIssuer.issue(tokenMetadata);
        DecodedJWT actual = getDecodedJWTFrom(result);

        assertSoftly(softly -> {
            softly.assertThat(actual.getSubject()).isEqualTo(FixtureTokenMetadata.SUBJECT);
            softly.assertThat(actual.getClaim(FixtureTokenMetadata.CLAIM).toString())
                .isEqualTo("\"" + FixtureTokenMetadata.USER_KEY + "\"");
        });
    }


    @Nested
    @DisplayName("TokenMetadata에 claim이")
    class TokenMetadataClaim {

        @Test
        @DisplayName("있을땐 claim이 존재하는 JWT 토큰으로 반환")
        void shouldReturnJwtTokenWithClaimWhenClaimExists() {
            TokenMetadata tokenMetadataWithClaim = FixtureTokenMetadata.createTokenMetadata();
            String result = auth0TokenIssuer.issue(tokenMetadataWithClaim);
            Claim actual = getDecodedJWTFrom(result).getClaim(FixtureTokenMetadata.CLAIM);

            assertThat(actual).isNotNull();
        }

        @Test
        @DisplayName("없을땐 claim이 존재하지 않는 JWT 토큰으로 반환")
        void shouldReturnJwtTokenWithoutClaimWhenClaimDoesNotExist() {
            TokenMetadata tokenMetadataWithOutClaim = FixtureTokenMetadata.createTokenMetadataWithOutClaim();
            String result = auth0TokenIssuer.issue(tokenMetadataWithOutClaim);
            Claim actual = getDecodedJWTFrom(result).getClaim(FixtureTokenMetadata.CLAIM);

            assertThat(actual.isNull()).isTrue();
        }

    }

    private DecodedJWT getDecodedJWTFrom(String token) {
        return JWT.require(Algorithm.HMAC256(FixtureTokenMetadata.SECRET)).build()
            .verify(token);
    }

}
