/*
package com.brevitaz.config;

import com.brevitaz.dao.EmployeeDao;
import com.brevitaz.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework  .security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        Employee employee=null;
        String id="1";
        try {
            employee=employeeDao.getById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GrantedAuthority authority=new SimpleGrantedAuthority(employee.getRole());

        User user=new User(employee.getFirstName(),employee.getPassword(), Arrays.asList(authority));

        UserDetails userDetails=(UserDetails)user;

        return userDetails;
    }
}*/
