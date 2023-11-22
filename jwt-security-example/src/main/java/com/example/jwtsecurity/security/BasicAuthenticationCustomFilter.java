package com.example.jwtsecurity.security;
import com.example.jwtsecurity.common.YN;
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

        if(isGuest(request.getHeader("isGuest"))) {
            chain.doFilter(request, response);
            return;
        }

        super.doFilterInternal(request, response, chain);
    }

    private boolean isPermitted(final String requestURI) {
        return PermitUrls.isPermitted(requestURI);
    }

    private boolean isGuest(final String isGuest) {
        return YN.isY(isGuest);
    }

}
