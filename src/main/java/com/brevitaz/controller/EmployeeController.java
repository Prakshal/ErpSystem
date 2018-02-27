package com.brevitaz.controller;

import com.brevitaz.dao.EmployeeDao;
import com.brevitaz.model.Employee;
import com.brevitaz.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@RequestBody Employee employee) throws IOException {
        return employeeService.create(employee);
    }


    @RequestMapping(method = {RequestMethod.GET})
    public List<Employee> getAll() throws IOException {
        return employeeService.getAll();
    }

    @PreAuthorize("hasAnyRole('employee')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean update(@RequestBody Employee employee,@PathVariable String id) throws IOException {
        return employeeService.update(employee,id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String id) throws IOException {
       return employeeService.delete(id);
    }


    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public Employee getById(@PathVariable String id) throws IOException {
        return employeeService.getById(id);
    }

    @RequestMapping(value = "/username/{username}", method = {RequestMethod.GET}) // just to test
    public Employee getUsername(@PathVariable String username, String password) throws IOException {
             return employeeService.getByUsername(username,password);
    }
}