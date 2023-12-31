package com.example.jwtsecurity.fixture;

import com.example.jwtsecurity.security.jwt.token.TokenMetadata;

public class FixtureTokenMetadata {
  public static String SECRET = "testSecretKey1234567812345678";

  public static final String CLAIM = "testClaim";

  public static final String USER_ID = "testUserId";

  public static String SUBJECT = "testSubject";

  public static String PREFIX = "testPrefix";

  public static long EXPIRATION_AT = 1000000L;

  public static TokenMetadata createTokenMetadata () {
	return TokenMetadata.of(USER_ID, SECRET, SUBJECT, EXPIRATION_AT, PREFIX, CLAIM);
  }

  public static TokenMetadata createTokenMetadataWithOutClaim () {
	return TokenMetadata.of(USER_ID, SECRET, SUBJECT, EXPIRATION_AT, PREFIX, null);
  }

  public static TokenMetadata createTokenMetadataWithExpirationAt (final Long expirationAt) {
	return TokenMetadata.of(USER_ID, SECRET, SUBJECT, expirationAt, PREFIX, CLAIM);
  }

  public static TokenMetadata createTokenMetadataWithPrefix (final String prefix) {
	return TokenMetadata.of(USER_ID, SECRET, SUBJECT, EXPIRATION_AT, prefix, CLAIM);
  }

  public static TokenMetadata createAccessTokenMetadata () {
	return TokenMetadata.of(USER_ID, SECRET, SUBJECT, EXPIRATION_AT, "Bearer ", CLAIM);
  }

  public static TokenMetadata createRefreshTokenMetadata () {
	return TokenMetadata.of(USER_ID, SECRET, SUBJECT, EXPIRATION_AT, "RefreshToken ", null);
  }

}
