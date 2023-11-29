package com.example.jwtsecurity.security.jwt;

import com.example.jwtsecurity.security.jwt.token.Token;

import java.util.Objects;

public class JwtPayload {

	private Token accessToken;

	private Token refreshToken;

	private JwtPayload (final Token accessToken, final Token refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public static JwtPayload of (final Token accessToken, final Token refreshToken) {
		return new JwtPayload(accessToken, refreshToken);
	}
	public Token getAccessToken () {
		return accessToken;
	}

	public Token getRefreshToken () {
		return refreshToken;
	}

	@Override
	public boolean equals (final Object o) {
		if (this == o) return true;
		if (!(o instanceof JwtPayload that)) return false;
		return Objects.equals(accessToken, that.accessToken)
			&& Objects.equals(refreshToken, that.refreshToken);
	}

	@Override
	public int hashCode () {
		return Objects.hash(accessToken, refreshToken);
	}

	public boolean isEmpty () {
		return Objects.isNull(accessToken) || Objects.isNull(refreshToken);
	}

}
