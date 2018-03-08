package com.brevitaz.controller;

import com.brevitaz.model.Employee;
import com.brevitaz.security.JwtGenerator;
import com.brevitaz.security.TokenProvider;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController
{
    @Autowired
    private TokenProvider tokenProvider;

    @RequestMapping(method = RequestMethod.POST)
    public String generate(@RequestBody final Employee employee)
    {
        return tokenProvider.generate(employee);
    }
}