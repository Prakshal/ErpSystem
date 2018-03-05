package com.brevitaz.security;

import com.brevitaz.model.Employee;
import com.brevitaz.model.Right;
import com.brevitaz.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtValidator
{
    private String secret = "brevitaz";

    public Employee validate(String token)
    {
        Employee employee=null;
        try
        {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            employee=new Employee();

            employee.setEmailId(body.getSubject());

            employee.setId((String)body.get("id"));

            employee.setFirstName((String)body.get("firstName"));

            employee.setLastName((String)body.get("lastName"));

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