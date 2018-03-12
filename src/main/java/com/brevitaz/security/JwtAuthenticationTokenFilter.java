package com.brevitaz.security;

import com.brevitaz.model.EmployeeDetails;
import com.brevitaz.model.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter
{
    public JwtAuthenticationTokenFilter()
    {
        super("/api/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException
    {
        String header = httpServletRequest.getHeader("Authorization");
        System.out.println(header);

        if(header == null || !header.startsWith("Bearer "))
        {
            throw new RuntimeException("JWT Token is Missing");
        }
        String authenticationToken = header.substring(7);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request,response);
    }



  /*  @Autowired
    private TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String header = httpServletRequest.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer "))
        {
            throw new RuntimeException("JWT Token is Missing");
        }
        else
        {
            String authenticationToken = header.substring(7);
            JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);

            EmployeeDetails employee = tokenProvider.validate(authenticationToken);
        }
        chain.doFilter(req, res);
    }
*/
    /*
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {

        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        roles.forEach(role -> role.getRight().forEach(right -> stringBuilder.append(right.getName()).append(",")));
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);

        grantedAuthorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList(substring));

        return grantedAuthorities;
    }*/
}