package com.brevitaz.SpringSecurityWithJwt.security;

import com.brevitaz.SpringSecurityWithJwt.model.JwtUser;
import com.brevitaz.SpringSecurityWithJwt.model.Right;
import com.brevitaz.SpringSecurityWithJwt.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.sun.org.apache.xerces.internal.impl.xpath.regex.CaseInsensitiveMap.get;

@Component
public class JwtValidator
{
    private String secret = "brevitaz";

    public JwtUser validate(String token)
    {
        JwtUser jwtUser=null;
        try
        {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser=new JwtUser();

            jwtUser.setUserName(body.getSubject());

            jwtUser.setPassword((String)body.get("password"));

            jwtUser.setId(Long.parseLong((String)body.get("userId")));

            List<LinkedHashMap<String,Object>> roles = (List<LinkedHashMap<String,Object>>)body.get("role");
//            List<LinkedHashMap<String,String>> rights = (List<LinkedHashMap<String,String>>) roles.get(0).get("rights");

            List<Role> roleList = roles.stream().map(role -> {
                List<Right> rights = ((List<LinkedHashMap<String, String>>) role.get("rights"))
                        .stream().map(right -> new Right(right.get("id"), right.get("name"))).collect(Collectors.toList());
                Role r = new Role();
                r.setRights(rights);
                r.setName(role.get("name").toString());
                r.setId(role.get("id").toString());
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
            jwtUser.setRoles(roleList);
            System.out.println(jwtUser.getRoles());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return jwtUser;
    }
}