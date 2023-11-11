package com.example.jwtsecurity.auth.jwt.token;
import java.time.LocalDateTime;
public class TokenFixture {

        public static TokenMetadata createTokenMetadata(long expirationTime) {
                return TokenMetadata.of("user1", "secretKey", "subject", expirationTime, "claimValue");
        }

        public static TokenMetadata createTokenMetadataOfSecretWithClaim(String secret, String claim) {
                return TokenMetadata.of("user1", secret, "subject", 1000000, "claimValue");
        }

        public static TokenMetadata createTokenMetadataOfSecretWithOutClaim(String secret) {
                return TokenMetadata.of("user1", secret, "subject", 1000000, null);
        }



}
