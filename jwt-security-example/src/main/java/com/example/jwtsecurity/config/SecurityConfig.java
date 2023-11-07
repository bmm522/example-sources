package com.example.jwtsecurity.config;

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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private static int BCryptPasswordStrength = 4;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http.csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                    .formLogin(AbstractHttpConfigurer::disable)
                    .httpBasic(AbstractHttpConfigurer::disable);
                return http.build();
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder(BCryptPasswordStrength);
        }

//        public class MyCustomSecurity extends AbstractHttpConfigurer<MyCustomSecurity, HttpSecurity> {
//                @Override
//                public void configure(HttpSecurity http) throws Exception {
//
//                        AuthenticationManager authenticationManager = http.getSharedObject(
//                            AuthenticationManager.class);
//                        http
//                            .addFilter(
//                                new JwtAuthenticationFilter(authenticationManager))
//                            .addFilter(new LoginAuthenticationFilter(authenticationManager, userDao,
//                                objectMapper));
//                }
//
//        }


}