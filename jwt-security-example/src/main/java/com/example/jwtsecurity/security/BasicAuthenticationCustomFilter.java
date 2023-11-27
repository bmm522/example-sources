package com.example.jwtsecurity.security;
import com.example.jwtsecurity.security.cookie.CookieParser;
import com.example.jwtsecurity.security.jwt.JwtPayload;
import com.example.jwtsecurity.security.jwt.JwtService;
import com.example.jwtsecurity.security.jwt.token.Token;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Objects;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
public class BasicAuthenticationCustomFilter extends BasicAuthenticationFilter {

    private final JwtService jwtService;

    private final String ACCESS_TOKEN_COOKIE_NAME = "AccessToken";
    private final String REFRESH_TOKEN_COOKIE_NAME = "RefreshToken";

    public BasicAuthenticationCustomFilter(final AuthenticationManager authenticationManager, final JwtService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response
        , final FilterChain chain) throws IOException, ServletException {


        if(isPermitted(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        if(isGuest(request.getCookies())){
            chain.doFilter(request, response);
            return;
        }




        super.doFilterInternal(request, response, chain);
    }

    private boolean isPermitted(final String requestURI) {
        return PermitUrls.isPermitted(requestURI);
    }

    private boolean isGuest(final Cookie[] cookies) {
        return Objects.isNull(getJwtPayloadFromCookies(cookies));
    }

    private JwtPayload getJwtPayloadFromCookies(Cookie[] cookies) {
        String accessTokenValue = CookieParser.getValue(cookies, ACCESS_TOKEN_COOKIE_NAME);
        String refreshTokenValue = CookieParser.getValue(cookies, REFRESH_TOKEN_COOKIE_NAME);

        return jwtService.generatePayload(accessTokenValue, refreshTokenValue);
    }
}
