package com.example.jwtsecurity.config;

import com.example.jwtsecurity.security.BasicAuthenticationCustomFilter;
import com.example.jwtsecurity.security.jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private static int BCryptPasswordStrength = 4;

  private final ObjectMapper objectMapper;

  private final JwtService jwtService;

  private static final String allowedOriginUrl = "http://localhost:8080";

  @Bean
  public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
	http.csrf(AbstractHttpConfigurer::disable)
		.sessionManagement(
			session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(request -> request.anyRequest().permitAll())
		.formLogin(AbstractHttpConfigurer::disable)
		.httpBasic(AbstractHttpConfigurer::disable)
		.cors(Customizer.withDefaults())
		.apply(new SecurityFilters());
	return http.build();
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder () {
	return new BCryptPasswordEncoder(BCryptPasswordStrength);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource () {
	CorsConfiguration configuration = new CorsConfiguration();
	configuration.setAllowedOrigins(List.of(allowedOriginUrl));
	configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
	configuration.setAllowedHeaders(Collections.singletonList("*"));
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", configuration);
	return source;
  }

  public class SecurityFilters extends AbstractHttpConfigurer<SecurityFilters, HttpSecurity> {

	@Override
	public void configure (HttpSecurity http) throws Exception {
	  AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
	  http.addFilter(new BasicAuthenticationCustomFilter(authenticationManager, jwtService));
	  //                .addFilter(new LoginAuthenticationFilter(authenticationManager, userDao, objectMapper));
	}

  }

}