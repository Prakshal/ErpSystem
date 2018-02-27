package com.brevitaz.service;

import com.brevitaz.dao.EmployeeDao;
import com.brevitaz.model.CustomEmployee;
import com.brevitaz.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        Employee employee=null;
        try {
            employee=employeeDao.getById("1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomEmployee customEmployee=(CustomEmployee)employee;
        System.out.println(customEmployee);
        return customEmployee;
    }
}
