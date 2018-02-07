package com.brevitaz.controller;

import com.brevitaz.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{

    private List<Employee> employees =new ArrayList<>();

    @RequestMapping(method = {RequestMethod.POST})
    public boolean create(@RequestBody Employee employee)
    {
        System.out.println("Registered Successfully");
        return true;
    }


    @RequestMapping(method = {RequestMethod.GET})
    public List<Employee> getAll()
    {
        System.out.println("All the Data Show here");
        return employees;
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT})
    public boolean update(@RequestBody Employee employee,@PathVariable String id)
    {
        System.out.println("Updated Successfully");
        return true;
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String id)
    {
        System.out.println("Deleted Successfully");
        return true;
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public Employee getById(@PathVariable String id)
    {
        System.out.println("Get By Id method called");
        return new Employee();
    }
}