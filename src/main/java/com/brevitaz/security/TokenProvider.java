package com.brevitaz.security;


import com.brevitaz.model.Employee;
import com.brevitaz.model.EmployeeDetails;
import com.brevitaz.model.Right;
import com.brevitaz.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenProvider
{
    @Value("${secretKey}")
    private String secretKey;


    public String generate(Object object)
    {
        Employee employee=(Employee) object;
        Claims claims = Jwts.claims()
                .setSubject(employee.getId());

        StringBuilder stringBuilder=new StringBuilder();
        employee.getRole().forEach(role -> role.getRight().forEach(right -> stringBuilder.append(right.getName()).append(",")));
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);

        //claims.put("role",employee.getRole());
        claims.put("rights",substring);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public EmployeeDetails validate(String token)
    {
        EmployeeDetails employee=null;
        try
        {
            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            /*List<LinkedHashMap<String,Object>> roles = (List<LinkedHashMap<String,Object>>)body.get("role");

            List<Role> roleList = roles.stream().map(role -> {
                List<Right> rights = ((List<LinkedHashMap<String, String>>) role.get("right"))
                        .stream().map(right -> new Right(right.get("id"), right.get("name"))).collect(Collectors.toList());
                Role r = new Role();
                r.setRight(rights);
                r.setName(role.get("name").toString());
                r.setId(role.get("id").toString());
                System.out.println("RIGHTS  "+rights);
                System.out.println("ROLE "+r);
                return r;
            }).collect(Collectors.toList());
*/


            String rights = (String) body.get("rights");
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(rights);


            /*List<GrantedAuthority> grantedAuthorities = null;
            grantedAuthorities = mapToGrantedAuthorities(roleList);*/

            return new EmployeeDetails(body.getSubject(),token,grantedAuthorities);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return employee;
    }
    /*private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {

        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        roles.forEach(role -> role.getRight().forEach(right -> stringBuilder.append(right.getName()).append(",")));
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);

        grantedAuthorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList(substring));

        return grantedAuthorities;
    }*/
}