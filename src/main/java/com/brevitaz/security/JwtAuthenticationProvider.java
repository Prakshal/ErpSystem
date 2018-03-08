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
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{

    @Autowired
    private JwtValidator jwtValidator;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;

        String token = jwtAuthenticationToken.getToken();

        Employee employee = tokenProvider.validate(token);

        if (employee == null) {
            throw new RuntimeException("JWT Token is incorrect");
        }

        List<GrantedAuthority> grantedAuthorities = null;
        EmployeeDetails employeeDetails=null;

        if(employeeDetails == null)
        {
            grantedAuthorities = mapToGrantedAuthorities(employee.getRole());
            employeeDetails = new EmployeeDetails(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmailId(),
                    token,
                    grantedAuthorities
            );
            return employeeDetails;
        }
        else
            return employeeDetails;
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {

        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        roles.forEach(role -> role.getRight().forEach(right -> stringBuilder.append(right.getName()).append(",")));
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);

        grantedAuthorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList(substring));

        return grantedAuthorities;
    }
}