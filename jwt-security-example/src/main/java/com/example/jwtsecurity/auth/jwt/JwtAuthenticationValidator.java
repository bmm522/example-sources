package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.Payload;
import com.example.jwtsecurity.auth.jwt.token.Token;
import io.jsonwebtoken.Jwts;
import java.util.Base64;
import org.springframework.stereotype.Component;

import com.example.jwtsecurity.auth.AuthenticationValidator;


@Component
public class JwtAuthenticationValidator implements AuthenticationValidator {

        @Override
        public void validate() {
                System.out.println("jwt validate");
        }
        @Override
        public boolean isExpiredAccessToken(final Token accessToken, final String secretKey) {
                return false;
        }
        @Override
        public boolean isExpiredRefreshToken(final Token refreshToken, final String secretKey) {
                return false;
        }


}
