package com.kamkry.app.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kamkry.app.domain.user.User;
import com.kamkry.app.domain.user.exception.UserExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.kamkry.app.config.SecurityConstants.*;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = mapUserFromRequest(request);
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    user.getUserRoles()
            );
            return authenticationManager.authenticate(token);
        } catch (InternalAuthenticationServiceException exception) {
            createErrorResponse(response, user);
        }
        return null;
    }

    private User mapUserFromRequest(HttpServletRequest request) {
        try {
            return new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createErrorResponse(HttpServletResponse response, User user) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new Gson().toJson(
                new UserExceptionResponse(
                        HttpStatus.NOT_FOUND.value(),
                        user.getUsername() + " doesn't exist",
                        System.currentTimeMillis()
                )
        );
        returnErrorResponse(response, json);
    }

    private void returnErrorResponse(HttpServletResponse response, String json) {
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String token = createToken(user);
        response.getWriter().write("{\"" + TOKEN_HEADER + "\":\"" + TOKEN_PREFIX + token + "\"}");
    }

    private String createToken(User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withArrayClaim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET));
    }
}
