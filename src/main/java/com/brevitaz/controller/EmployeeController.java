package com.brevitaz.controller;

import com.brevitaz.model.Employee;
import com.brevitaz.model.Right;
import com.brevitaz.model.Role;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{

    private List<Employee> employees =new ArrayList<>();

    @RequestMapping(method = {RequestMethod.POST})
    public void register(@RequestBody Employee employee)
    {
        System.out.println("Registered Successfully");
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public void login(@RequestBody Employee employee)
    {
        System.out.println(employee.getName()+" "+employee.getPassword());
        System.out.println("Login Successfully");
    }

    @RequestMapping(value = "/viewEmployee", method = {RequestMethod.GET})
    public List<Employee> viewEmployee()
    {
        System.out.println("All the Data Show here");
        return employees;
    }

    @RequestMapping(value = "/updateEmployee/id={id}", method = {RequestMethod.PUT})
    public void updateEmployee(@RequestBody Employee employee,@PathVariable String id)
    {
        System.out.println("Updated Successfully");
    }

    @RequestMapping(value = "/deleteEmployee/id={id}", method = {RequestMethod.DELETE})
    public void deleteEmployee(@PathVariable String id)
    {
        System.out.println("Deleted Successfully");
    }

    @RequestMapping(value = "/getById/id={id}", method = {RequestMethod.GET})
    public void getById(@PathVariable String id)
    {
        System.out.println("Get By Id method called");
    }

    @RequestMapping(value = "/createRole", method = {RequestMethod.POST})
    public void createRole(@RequestBody Role role)
    {
        System.out.println("Create Role method called");
    }

    @RequestMapping(value = "/viewRole", method = {RequestMethod.GET})
    public List<Role> viewRoles()
    {
        System.out.println("All the Data Show here");
        return null;
    }

    @RequestMapping(value = "/deleteRole/id={id}", method = {RequestMethod.DELETE})
    public void deleteRole(@PathVariable String id)
    {
        System.out.println("Deleted Successfully");
    }


    @RequestMapping(value = "/createRights", method = {RequestMethod.POST})
    public void createRights(@RequestBody Right rights)
    {
        System.out.println("Create Rights method called");
    }

    @RequestMapping(value = "/updateRights/id={id}", method = {RequestMethod.PUT})
    public void updateRights(@RequestBody Right rights,@PathVariable String id)
    {
        System.out.println("Updated Successfully");
    }

    @RequestMapping(value = "/viewRights", method = {RequestMethod.GET})
    public List<Right> viewRights()
    {
        System.out.println("All the Rights Show here");
        return null;
    }
    @RequestMapping(value = "/viewRights/{id}", method = {RequestMethod.GET})
    public Right viewRights(@PathVariable String id)
    {
        System.out.println("All the Rights Show here");
        return null;
    }
    @RequestMapping(value = "/deleteRights/id={id}", method = {RequestMethod.DELETE})
    public void deleteRights(@PathVariable String id)
    {
        System.out.println("Deleted Successfully");
    }
}