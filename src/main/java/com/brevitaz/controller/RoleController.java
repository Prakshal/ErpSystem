package com.brevitaz.controller;

import com.brevitaz.model.Role;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @RequestMapping(method = {RequestMethod.POST})
    public boolean createRole(@RequestBody Role role) {
        System.out.println("ROle is created");
        return true;
    }

    @RequestMapping(value="/{roleId}/assign/{employeeId}",method = {RequestMethod.POST})
    public boolean assignRole(@PathVariable String roleId,String employeeId)
    {
        System.out.println("Assign roles Successfully");
        System.out.println("Role "+roleId+ " to employee "+employeeId);
        return true;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public void getRoles()
    {
        System.out.println("Get roless successfully");
    }

    @RequestMapping(value = "/{roleId}",method = {RequestMethod.DELETE})
    public boolean deleteRole(@PathVariable String roleId)
    {
        System.out.println("Role is deleted");
        return true;
    }
}
