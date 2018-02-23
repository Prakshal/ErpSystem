package com.brevitaz.service.impl;

import com.brevitaz.model.Employee;
import com.brevitaz.service.EmployeeService;
import com.brevitaz.service.LogInService;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;

@Service
public class LogInServiceImpl implements LogInService {

    @Autowired
    EmployeeService employeeService;

    @Override
    public ResponseEntity<String> login(String username, String password){
        byte[] pwd = Base64.getDecoder().decode(password.getBytes());
        String pass = new String(pwd);
        Employee employee = employeeService.getByUsernameAndPassword(username,pass);

        if(employee!=null)
            return new ResponseEntity<>("Authorized", HttpStatus.OK);
        else
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);

    }

    @Override
    public boolean logout() {
        return false;
    }
}
