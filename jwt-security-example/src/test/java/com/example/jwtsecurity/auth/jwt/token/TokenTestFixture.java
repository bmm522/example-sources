package com.example.jwtsecurity.auth.jwt.token;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.jwtsecurity.auth.AuthenticationAble;

@ExtendWith(MockitoExtension.class)
public class TokenTestFixture {

	@Mock
	TokenProperties tokenProperties;

    String secret = "testSecretKey1234567812345678";

    String claimName ="testClaim";

    String userKey = "testUserKey";

	String subject = "testSubject";

	long expirationTime = 1000000L;

	public TokenMetadata createTokenMetadata (long expirationTime) {
		return TokenMetadata.of("user1", "secretKey", "subject", expirationTime, "claimValue");
	}

	public TokenMetadata createTokenMetadataOfSecretWithClaim (String secret, String claim) {
		return TokenMetadata.of("user1", secret, "subject", 1000000, "claimValue");
	}

	public TokenMetadata createTokenMetadataOfSecretWithOutClaim (String secret) {
		return TokenMetadata.of("user1", secret, "subject", 1000000, null);
	}

	public TestUser getTestUser () {
		return new TestUser();
	}
	class TestUser implements AuthenticationAble {

		@Override
		public String getUserKey () {
			return "testUserKey";
		}

	}

}
