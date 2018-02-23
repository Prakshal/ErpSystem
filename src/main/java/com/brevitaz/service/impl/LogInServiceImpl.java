package com.brevitaz.service.impl;

import com.brevitaz.model.Employee;
import com.brevitaz.service.EmployeeService;
import com.brevitaz.service.LogInService;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;

@Service
public class LogInServiceImpl implements LogInService {

    @Autowired
    EmployeeService employeeService;

    @Override
    public String login(String username, String password) throws IOException {
        byte[] pwd = Base64.getDecoder().decode(password.getBytes());
        String pass = new String(pwd);
        Employee employee = employeeService.getByUsername(username,pass);

       /* String response;
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
*/
       return "de";
    }

    @Override
    public boolean logout() {
        return false;
    }
}
