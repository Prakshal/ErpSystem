package com.brevitaz.controller;

import com.brevitaz.model.Employee;
import com.brevitaz.security.TokenProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("token")
public class TokenController
{
    private TokenProvider tokenProvider;

    public TokenController(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String generate(@RequestBody final Employee employee)
    {
        return tokenProvider.generate(employee);
    }
}