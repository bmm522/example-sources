package com.example.jwtsecurity.auth;

public interface Payload {

	String getAccessToken();

	String getRefreshToken();
}
