package com.example.jwtsecurity.auth.jwt.token.fixture;
import com.example.jwtsecurity.auth.jwt.token.AccessToken;
import com.example.jwtsecurity.auth.jwt.token.RefreshToken;
import com.example.jwtsecurity.auth.jwt.token.Token;
public class FixtureToken {

    public static Token createAccessToken() {
        return AccessToken.of("testAccessToken");
    }

    public static Token createRefreshToken() {
        return RefreshToken.of("testRefreshToken");
    }

}
