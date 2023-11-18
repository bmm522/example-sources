package com.example.jwtsecurity.auth.jwt;

import com.example.jwtsecurity.auth.jwt.token.Token;
import org.springframework.stereotype.Component;


@Component
public class JwtValidator {


        public void validate() {
                System.out.println("jwt validate");
        }

        public boolean isExpiredAccessToken(final Token accessToken, final String secretKey) {
                return true;
        }

        public boolean isExpiredRefreshToken(final Token refreshToken, final String secretKey) {
                return false;
        }


}
