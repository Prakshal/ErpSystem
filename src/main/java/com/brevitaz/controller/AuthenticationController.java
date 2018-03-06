package com.brevitaz.controller;

import com.brevitaz.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@RestController
@RequestMapping("/api")

public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

   /* @RequestMapping(value = "/")
    public String getLogin()
    {
        return "login";
    }*/

    /*@RequestMapping(value = "/welcome")
    public String getWelcome()
    {
        return "welcome";
    }*/

    @RequestMapping(value = "/login" ,method = {RequestMethod.POST})

    public @ResponseBody ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password,HttpServletResponse httpServletResponse){
        return authenticationService.login(username,password);
    }

    @RequestMapping(value = "/logout",method = {RequestMethod.POST})
    public boolean logout()
    {
        System.out.println("Logout successfully");
        return true;
    }
}
