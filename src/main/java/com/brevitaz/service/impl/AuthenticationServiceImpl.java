package com.brevitaz.service.impl;

import com.brevitaz.model.Employee;
import com.brevitaz.service.EmployeeService;
import com.brevitaz.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    EmployeeService employeeService;

    @Override
    public ResponseEntity<String> login(String username, String password){
       /* byte[] pwd = Base64.getDecoder().decode(password.getBytes());
        String pass = new String(pwd);*/
        if(username.trim().length()<=0||password.trim().length()<=0)
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);

        String pass = Base64.getEncoder().encodeToString(password.getBytes());

        Employee employee = employeeService.getByUsernameAndPassword(username,pass);

        if(employee!=null) {
            return new ResponseEntity<>("Authorized",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }



    @Override
    public boolean logout() {
        return false;
    }
}