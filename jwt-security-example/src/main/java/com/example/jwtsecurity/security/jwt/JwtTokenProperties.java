package com.example.jwtsecurity.security.jwt;

import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "token")
public class JwtTokenProperties {

  private final AccessToken accessToken = new AccessToken();
  private final RefreshToken refreshToken = new RefreshToken();

  private String secret;

  public String getSecretKey () {
	return secret;
  }
  public String getAccessTokenPrefix () {
	return accessToken.prefix;
  }

  public String getAccessTokenClaim () {
	return accessToken.claim;
  }
  public String getAccessTokenSubject () {
	return accessToken.subject;
  }
  public long getAccessTokenExpirationTime () {
	return accessToken.expirationTime;
  }
  public String getRefreshTokenSubject () {
	return refreshToken.subject;
  }
  public long getRefreshTokenExpirationTime () {
	return refreshToken.expirationTime;
  }

  public String getRefreshTokenPrefix () {
	return refreshToken.prefix;
  }

  public String getAccessTokenCookieName () {
	return accessToken.cookieName;
  }

  public String getRefreshTokenCookieName () {
	return refreshToken.cookieName;
  }

  @Setter
  static class AccessToken {
	private String prefix;
	private String subject;
	private long expirationTime;
	private String claim;
	private String cookieName;
  }

  @Setter
  static class RefreshToken {
	private String prefix;
	private String subject;
	private long expirationTime;
	private String cookieName;

  }

}
