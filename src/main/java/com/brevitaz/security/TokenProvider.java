package com.brevitaz.security;

import com.brevitaz.model.Employee;
import com.brevitaz.model.Right;
import com.brevitaz.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenProvider
{
    @Value("${secretKey}")
    private String secretKey;

    public String generate(Employee employee)
    {
        Claims claims = Jwts.claims()
                .setSubject(employee.getId());

        claims.put("role",employee.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Employee validate(String token)
    {
        Employee employee=null;
        try
        {
            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            employee=new Employee();

            List<LinkedHashMap<String,Object>> roles = (List<LinkedHashMap<String,Object>>)body.get("role");

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
            employee.setRole(roleList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return employee;
    }
}
