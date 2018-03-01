package com.brevitaz.SpringSecurityWithJwt.security;


import com.brevitaz.SpringSecurityWithJwt.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


@Component
public class JwtGenerator
{
    public String generate(JwtUser jwtUser)
    {
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());

        claims.put("password",jwtUser.getPassword());
        claims.put("userId",String.valueOf(jwtUser.getId()));
        claims.put("role",jwtUser.getRoles());
/*        StringBuilder stringBuilder=new StringBuilder();
        jwtUser.getRoles().forEach(role -> role.getRights().forEach(right -> stringBuilder.append(right.getName()).append(",")));
        String subString = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        claims.put("authority",subString);*/

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "brevitaz")
                .compact();
    }
}