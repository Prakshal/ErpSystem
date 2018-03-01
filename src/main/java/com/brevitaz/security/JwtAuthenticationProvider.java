package com.brevitaz.SpringSecurityWithJwt.security;

import com.brevitaz.SpringSecurityWithJwt.model.JwtAuthenticationToken;
import com.brevitaz.SpringSecurityWithJwt.model.JwtUser;
import com.brevitaz.SpringSecurityWithJwt.model.JwtUserDetails;
import com.brevitaz.SpringSecurityWithJwt.model.Role;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

        /*@Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name=authentication.getName().trim();
        System.out.println(name);
        //String password=authentication.getCredentials().toString().trim();
        System.out.println("Authenticate Method Called");

        if("Yash".equals(name))
        {
            System.out.println("Authenticated");
            return new UsernamePasswordAuthenticationToken(name,null);
        }
        else {
            throw new BadCredentialsException("Authentication Failes");
        }
    }*/

    @Autowired
    public JwtValidator jwtValidator;


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }


    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;

        String token = jwtAuthenticationToken.getToken();


        JwtUser jwtUser = jwtValidator.validate(token);
        System.out.println("JWT"+jwtUser);

        if (jwtUser == null) {
            throw new RuntimeException("JWT Token is incorrect");
        }

     /*   List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(String.valueOf(jwtUser.getRole()));*/

     List<GrantedAuthority> grantedAuthorities= mapToGrantedAuthorities(jwtUser.getRoles());


         return new JwtUserDetails(jwtUser.getUserName(), jwtUser.getPassword(), jwtUser.getId(),
                token,
                grantedAuthorities
        );
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        /*return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());*/

        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        roles.forEach(r -> r.getRights().forEach(right -> stringBuilder.append(right.getName()).append(",")));
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("Substringg"+substring);

        grantedAuthorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList(substring));

        return grantedAuthorities;
    }
}