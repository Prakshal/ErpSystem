package com.brevitaz.service.impl;

import com.brevitaz.model.Employee;
import com.brevitaz.service.EmployeeService;
import com.brevitaz.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class LogInServiceImpl implements LogInService {

    @Autowired
    EmployeeService employeeService;

    @Override
    public String login(String username, String password) throws IOException {
        Employee employee = employeeService.getByUsername(username,password);

        String response;
        if(employee!=null)
        {
            response="true";

            return response;
        }
        else
        {
            response="false";
            return response;
        }

    }

    @Override
    public boolean logout() {
        return false;
    }
}
