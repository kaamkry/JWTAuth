package com.kamkry.app.infrastructure.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.kamkry.app.infrastructure.config.SecurityConstants.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String header = request.getHeader(TOKEN_HEADER);
        if (!isTokenCorrect(header)) {
            filterChain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthenticationEntryPoint(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);

    }

    private boolean isTokenCorrect(String header) {
        return !(header == null || !header.startsWith(TOKEN_PREFIX));
    }


    private UsernamePasswordAuthenticationToken getAuthenticationEntryPoint(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (token!=null) {
            DecodedJWT decodedJWT = decodeToken(token);
            String id = decodedJWT.getSubject();
            List<SimpleGrantedAuthority> authorityList = decodedJWT
                    .getClaim("authorities")
                    .asList(SimpleGrantedAuthority.class);
            return new UsernamePasswordAuthenticationToken(id, null, authorityList);
        }
        return null;
    }

    private DecodedJWT decodeToken(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""));
    }
}
