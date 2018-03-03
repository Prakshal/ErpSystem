package com.brevitaz.security;

import com.brevitaz.model.Employee;
import com.brevitaz.model.EmployeeDetails;
import com.brevitaz.model.JwtAuthenticationToken;
import com.brevitaz.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

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


        Employee employee = jwtValidator.validate(token);

        if (employee == null) {
            throw new RuntimeException("JWT Token is incorrect");
        }

     /*   List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(String.valueOf(jwtUser.getRole()));*/

     List<GrantedAuthority> grantedAuthorities= mapToGrantedAuthorities(employee.getRole());


        return new EmployeeDetails(employee.getId(),employee.getFirstName(),employee.getLastName(), employee.getPassword(),employee.getEmailId(),
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

        roles.forEach(r -> r.getRight().forEach(right -> stringBuilder.append(right.getName()).append(",")));
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);

        grantedAuthorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList(substring));

        return grantedAuthorities;
    }
}