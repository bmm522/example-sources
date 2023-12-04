package com.example.jwtsecurity.security;

import com.example.jwtsecurity.common.Role;
import com.example.jwtsecurity.domain.User;
import com.example.jwtsecurity.security.jwt.JwtPayload;
import com.example.jwtsecurity.security.jwt.JwtService;
import com.example.jwtsecurity.util.ResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

  private final JwtService jwtService;

  private final ObjectMapper objectMapper;

  public JwtAuthenticationFilter (final AuthenticationManager authenticationManager,
	  final JwtService jwtService, final ObjectMapper objectMapper) {
	super(authenticationManager);
	this.jwtService = jwtService;
	this.objectMapper = objectMapper;
  }

  @Override
  protected void doFilterInternal (final HttpServletRequest request, final HttpServletResponse response,
	  final FilterChain chain) throws IOException, ServletException {

	JwtPayload jwtPayload = getJwtPayloadFromCookies(request);

	if (isPermittedUrls(request)) {
	  chain.doFilter(request, response);
	  return;
	}

	if (isGuest(jwtPayload)) {
	  chain.doFilter(request, response);
	  return;
	}

	if(isValidPayload(jwtPayload)) {
	  handleException(HttpStatus.UNAUTHORIZED.value(), response);
	  return;
	}

	if(isAccessTokenExpired(jwtPayload)) {
	  Integer isExpiredStatusCode = 419;
	  handleException(isExpiredStatusCode, response);
	  return;
	}




	setContext(jwtPayload);
	chain.doFilter(request, response);
  }
  private boolean isValidPayload (JwtPayload jwtPayload) {
	
  }

  private boolean isAccessTokenExpired (JwtPayload jwtPayload) {
	return jwtService.isAccessTokenExpired(jwtPayload);
  }

  private boolean isPermittedUrls (final HttpServletRequest request) {
	return PermitUrls.isPermitted(request.getRequestURI());
  }

  private boolean isGuest (final JwtPayload jwtPayload) {
	return jwtPayload.isEmpty();
  }

  private JwtPayload getJwtPayloadFromCookies (HttpServletRequest request) {
	return jwtService.generatePayload(request.getCookies());
  }

  private void handleException (Integer statusCode , HttpServletResponse response) throws IOException {
	objectMapper.writeValue(response.getOutputStream(), ResponseUtils.fail(statusCode));
  }

  private void setContext(JwtPayload jwtPayload) {
	SecurityContextHolder.getContext().setAuthentication(getAuthentication(jwtPayload));
  }

  private Authentication getAuthentication (JwtPayload jwtPayload) {
	String userId = jwtService.getUserIdFromAccessToken(jwtPayload.getAccessToken());
	return new UsernamePasswordAuthenticationToken(User.of(userId), Role.USER);
  }
}
