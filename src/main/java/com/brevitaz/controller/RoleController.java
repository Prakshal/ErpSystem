package com.brevitaz.controller;

import com.brevitaz.model.Employee;
import com.brevitaz.model.Role;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private List<Role> roles =new ArrayList<>();

    @RequestMapping(method = {RequestMethod.POST})
    public boolean create(@RequestBody Role role) {
        System.out.println("Role is created");
        return true;
    }

    @RequestMapping(value="/{roleId}/assign/{employeeId}",method = {RequestMethod.POST})
    public boolean assign(@PathVariable String roleId,String employeeId)
    {
        System.out.println("Assign roles Successfully");
        System.out.println("Role "+roleId+ " to employee "+employeeId);
        return true;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Role> get()
    {
        System.out.println("Get roless successfully");
        return roles;
    }

    @RequestMapping(value = "/{roleId}",method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String roleId)
    {
        System.out.println("Role is deleted");
        return true;
    }
}
