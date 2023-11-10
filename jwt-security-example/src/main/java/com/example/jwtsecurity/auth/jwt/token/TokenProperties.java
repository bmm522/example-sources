package com.example.jwtsecurity.auth.jwt.token;

import lombok.Getter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
public class TokenProperties {

	private String accessTokenPrefix;
	private String accessTokenSubject;
	private long accessTokenExpirationTime;
	private String secret;
	private String accessTokenClaim;
	private String refreshTokenPrefix;
	private String refreshTokenSubject;
	private long refreshTokenExpirationTime;

}
