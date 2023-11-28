package com.example.jwtsecurity.security;
import com.example.jwtsecurity.security.jwt.JwtPayload;
import com.example.jwtsecurity.security.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
public class BasicAuthenticationCustomFilter extends BasicAuthenticationFilter {

    private final JwtService jwtService;


    public BasicAuthenticationCustomFilter(final AuthenticationManager authenticationManager,
        final JwtService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
        final HttpServletResponse response
        , final FilterChain chain) throws IOException, ServletException {

        if (isPermittedUrls(request)) {
            chain.doFilter(request, response);
            return;
        }

        JwtPayload jwtPayload = getJwtPayloadFromCookies(request);

        if (isGuest(jwtPayload)) {
            chain.doFilter(request, response);
            return;
        }


        super.doFilterInternal(request, response, chain);
    }

    private boolean isPermittedUrls(final HttpServletRequest request) {
        return PermitUrls.isPermitted(request.getRequestURI());
    }

    private boolean isGuest(final JwtPayload jwtPayload) {
        return jwtPayload.isEmpty();
    }

    private JwtPayload getJwtPayloadFromCookies(HttpServletRequest request) {
        return jwtService.generatePayload(request.getCookies());
    }

}
