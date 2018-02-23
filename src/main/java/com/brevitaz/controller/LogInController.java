package com.brevitaz.controller;

import com.brevitaz.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/")

public class LogInController {

    @Autowired
    LogInService logInService;

    @RequestMapping(value = "/")
    public String getLogin()
    {
        return "login";
    }

    @RequestMapping(value = "/welcome")
    public String getWelcome()
    {
        return "welcome";
    }

    @RequestMapping(value = "/login" ,method = {RequestMethod.POST})
    public @ResponseBody String login(@RequestParam("username") String username, @RequestParam("password") String password) throws IOException {
       return logInService.login(username,password);
    }

    @RequestMapping(value = "/logout",method = {RequestMethod.POST})
    public boolean logout()
    {
        System.out.println("Logout successfully");
        return true;
    }
}
