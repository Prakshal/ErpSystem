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
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PreAuthorize("hasAuthority('READ')")
    @RequestMapping(method = {RequestMethod.GET})
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean update(@RequestBody Employee employee, @PathVariable String id) {
        return employeeService.update(employee, id);
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String id) {
        return employeeService.delete(id);
    }


    @PreAuthorize("hasAuthority('READ')")
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public Employee getById(@PathVariable String id) {
        return employeeService.getById(id);
    }
}