package com.brevitaz.controller;

import com.brevitaz.model.Employee;
import com.brevitaz.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
public class TokenController
{
    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String generate(@RequestBody final Employee employee)
    {
        return jwtGenerator.generate(employee);
    }
}