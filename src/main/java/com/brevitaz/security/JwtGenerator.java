package com.brevitaz.security;

import com.brevitaz.model.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


@Component
public class JwtGenerator
{
    public String generate(Employee employee)
    {
        Claims claims = Jwts.claims()
                .setSubject(employee.getEmailId());

        claims.put("firstName",employee.getFirstName());
        claims.put("lastName",employee.getLastName());
        claims.put("password",employee.getPassword());
        claims.put("id",employee.getId());
        claims.put("role",employee.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "brevitaz")
                .compact();
    }
}