package com.brevitaz.SpringSecurityWithJwt.controller;

import com.brevitaz.SpringSecurityWithJwt.dao.UserDao;
import com.brevitaz.SpringSecurityWithJwt.model.JwtUser;
import com.brevitaz.SpringSecurityWithJwt.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController
{
    @Autowired
    private UserDao userDao;

    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser)
    {
        //JwtUser jwtUser1=userDao.getById(jwtUser.getId());
        return jwtGenerator.generate(jwtUser);
    }
}