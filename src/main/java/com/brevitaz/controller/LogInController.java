package com.brevitaz.controller;

import com.brevitaz.model.Employee;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")

public class LogInController {
    @RequestMapping(value = "/login" ,method = {RequestMethod.POST})
    public boolean login(@PathVariable String unma,String pass)
    {
        System.out.println("Login Successfully");
        return true;
    }

    @RequestMapping(value = "/logout",method = {RequestMethod.POST})
    public boolean logout()
    {
        System.out.println("Logout successfully");
        return true;
    }
}
