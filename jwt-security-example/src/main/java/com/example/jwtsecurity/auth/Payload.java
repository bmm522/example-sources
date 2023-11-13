package com.example.jwtsecurity.auth;

import com.example.jwtsecurity.auth.jwt.token.Token;
public interface Payload {

	Token getAccessToken();

	Token getRefreshToken();
}
