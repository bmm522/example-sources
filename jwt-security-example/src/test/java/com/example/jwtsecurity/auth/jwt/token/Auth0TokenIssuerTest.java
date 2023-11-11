package com.example.jwtsecurity.auth.jwt.token;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class Auth0TokenIssuerTest {

        private TokenIssuer auth0TokenIssuer;
        private String secret;
        private String claimName;
        private String userKey;
        private String subject;
        private long expirationTime;

        @BeforeEach
        void setUp() {
                auth0TokenIssuer = new Auth0TokenIssuer();
                secret = "testSecretKey1234567812345678";
                claimName = "testClaim";
                userKey = "testUserKey";
                subject = "testSubject";
                expirationTime = 1000000L;
        }

        @Test
        @DisplayName("TokenMetadata를 넣으면 해당 정보를 가진 Jwt이 반환된다.")
        void testTokenCreationFromMetadata() {

                TokenMetadata tokenMetadata = TokenMetadata.of(userKey, secret, subject,
                    expirationTime, claimName);

                String actualToken = auth0TokenIssuer.makeToken(tokenMetadata);

                String expectToken = JWT.create()
                    .withSubject(tokenMetadata.subject())
                    .withExpiresAt(tokenMetadata.getExpiresAtOfDateType(LocalDateTime.now()))
                    .withClaim(tokenMetadata.claim(), tokenMetadata.userKey())
                    .sign(Algorithm.HMAC256(tokenMetadata.secret()));

                DecodedJWT actual = JWT.require(Algorithm.HMAC256(secret)).build()
                    .verify(actualToken);
                DecodedJWT expect = JWT.require(Algorithm.HMAC256(secret)).build()
                    .verify(expectToken);

                assertSoftly(softly -> {
                        softly.assertThat(actual.getSubject()).isEqualTo(expect.getSubject());
                        softly.assertThat(actual.getClaim(claimName).toString()).isEqualTo(expect.getClaim(
                            claimName).toString());
                        softly.assertThat(actual.getSignature()).isEqualTo(expect.getSignature());
                });

        }

        @Nested
        @DisplayName("TokenMeatadata에 claim이")
        class TokenMetadataWithClaimsTest {

                @Test
                @DisplayName("있을땐 claim이 존재하는 JWT 토큰으로 반환")
                void whenClaimsPresentThenReturnJwtTokenWithClaims() {

                        TokenMetadata tokenMetadataWithClaim = TokenFixture.createTokenMetadataOfSecretWithClaim(
                            secret, claimName);
                        String token = auth0TokenIssuer.makeToken(tokenMetadataWithClaim);

                        Claim expect = JWT.require(Algorithm.HMAC256(secret)).build().verify(token)
                            .getClaim(claimName);

                        assertThat(expect).isNotNull();
                }

                @Test
                @DisplayName("없을땐 claim이 존재하지 않는 JWT 토큰으로 반환")
                void whenNoClaimsThenReturnJwtTokenWithoutClaims() {

                        TokenMetadata tokenMetadataWithOutClaim = TokenFixture.createTokenMetadataOfSecretWithOutClaim(
                            secret);
                        String token = auth0TokenIssuer.makeToken(tokenMetadataWithOutClaim);

                        Claim expect = JWT.require(Algorithm.HMAC256(secret)).build().verify(token)
                            .getClaim(claimName);

                        assertThat(expect.isNull()).isTrue();
                }


        }

}
