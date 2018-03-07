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

            employee.setPassword((String)body.get("password"));

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


            //List<Role> roles1=body.get("role",List.class);


            /*List<Role> r = new ArrayList<>();
            List<Right> rightList = new ArrayList<>();

            for (int i=0; i<roles.size(); i++) {
                Role role = new Role();
                role.setId(roles.get(i).get("id"));
                role.setName(roles.get(i).get("name"));
                //role.setRights()=roles.get(i).get("rights"));

                for(int j=0;i<roles.get(i).get("rights").length();j++)
                {
                    Right right=new Right();
                    right.setId(rights.get(j).get("id"));
                    right.setName(rights.get(j).get("name"));
                    rightList.add(right);
                }
                role.setRights(rightList);
                r.add(role);
            }*/

            //List<Role> rolesList = roles.stream().map(role -> new Role(role.get("id"),role.get("name"),role.get("role").)).collect(Collectors.toList());

//            jwtUser.setRoles(roles1);
            employee.setRole(roleList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return employee;
    }
}