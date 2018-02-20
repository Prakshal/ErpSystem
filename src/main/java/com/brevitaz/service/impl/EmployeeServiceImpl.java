package com.brevitaz.service.impl;

import com.brevitaz.dao.EmployeeDao;
import com.brevitaz.model.Employee;
import com.brevitaz.model.Role;
import com.brevitaz.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public boolean insert(Employee employee) throws IOException
    {
        return employeeDao.insert(employee);
    }

    @Override
    public Employee getById(String id) throws IOException
    {
        Employee employee=employeeDao.getById(id);

        //GrantedAuthority authority=new SimpleGrantedAuthority(employee.getRole());
        return employee;
    }
}
