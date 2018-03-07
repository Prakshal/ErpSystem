package com.brevitaz.security;

import com.brevitaz.model.JwtAuthenticationToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {


    public JwtAuthenticationTokenFilter() {
        super("/api/employee/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException
    {

        //String header = httpServletRequest.getHeader("Authorization");

       /* if(header == null || !header.startsWith("Bearer "))
        {
            throw new RuntimeException("JWT Token is Missing");
        }

        String authenticationToken = header.substring(7);*/

      /*  JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);*/
        /*return getAuthenticationManager().authenticate(token);*/
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request,response);
    }
}