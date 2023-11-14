package com.example.jwtsecurity.auth.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.LocalDateTime;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.example.jwtsecurity.auth.AuthenticationAble;
@ExtendWith(MockitoExtension.class)
public class TokenTestFixture {

    @Mock
    TokenProperties tokenProperties;

    public String secret = "testSecretKey1234567812345678";

    public String claimName = "testClaim";

    public String userKey = "testUserKey";

    public String subject = "testSubject";

    public String prefix = "testPrefix";

    long expirationTime = 1000000L;

    public TokenMetadata createTokenMetadata(long expirationTime) {
        return TokenMetadata.of("user1", "secretKey", "subject", expirationTime, "testPrefix",
            "claimValue");
    }

    public TokenMetadata createTokenMetadataOfSecretWithClaim(String secret, String claim) {
        return TokenMetadata.of("user1", secret, "subject", 1000000, "testPrefix", "claimValue");
    }

    public TokenMetadata createTokenMetadataOfSecretWithOutClaim(String secret) {
        return TokenMetadata.of("user1", secret, "subject", 1000000, "testPrefix", null);
    }

    public TestUser getTestUser() {
        return new TestUser();
    }

    public Token getAccessToken () {
        TokenMetadata accessTokenMetadata = createTokenMetadataOfSecretWithClaim(secret, claimName);

        return AccessToken.of(JWT.create()
            .withSubject(accessTokenMetadata.subject())
            .withExpiresAt(accessTokenMetadata.getExpiresAtOfDateType(LocalDateTime.now()))
            .withClaim(accessTokenMetadata.claim(), accessTokenMetadata.userKey())
            .sign(Algorithm.HMAC256(accessTokenMetadata.secret())));
    }

    public Token getRefreshToken() {
        TokenMetadata refreshTokenMetadata = createTokenMetadataOfSecretWithOutClaim(secret);

        return RefreshToken.of(JWT.create()
            .withSubject(refreshTokenMetadata.subject())
            .withExpiresAt(refreshTokenMetadata.getExpiresAtOfDateType(LocalDateTime.now()))
            .sign(Algorithm.HMAC256(refreshTokenMetadata.secret())));
    }
    class TestUser implements AuthenticationAble {

        @Override
        public String getUserKey() {
            return "testUserKey";
        }

    }

}
