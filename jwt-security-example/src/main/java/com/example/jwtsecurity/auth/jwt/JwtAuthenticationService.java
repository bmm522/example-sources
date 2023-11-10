package com.example.jwtsecurity.auth.jwt;

import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.AuthenticationAble;
import com.example.jwtsecurity.auth.AuthenticationGenerator;
import com.example.jwtsecurity.auth.AuthenticationService;
import com.example.jwtsecurity.auth.AuthenticationValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationService implements AuthenticationService<JwtPayload> {

	private final AuthenticationGenerator<JwtPayload> authenticationGenerator;

	private final AuthenticationValidator authenticationValidator;

	public JwtPayload generatePayload (AuthenticationAble authenticationAble) {

		return authenticationGenerator.generate(authenticationAble);
	}

	public void validate () {

		authenticationValidator.validate();
	}

}
