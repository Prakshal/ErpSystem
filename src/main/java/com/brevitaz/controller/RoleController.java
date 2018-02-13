package com.brevitaz.controller;

import com.brevitaz.dao.RoleDao;
import com.brevitaz.model.Employee;
import com.brevitaz.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleDao dao;

    @RequestMapping(method = {RequestMethod.POST})
    public boolean create(@RequestBody Role role) throws IOException {
        return dao.create(role);
    }

    @RequestMapping(value="/{roleId}/assign/{employeeId}",method = {RequestMethod.POST})
    public boolean assign(@PathVariable String roleId,String employeeId)
    {
        System.out.println("Assign roles Successfully");
        System.out.println("Role "+roleId+ " to employee "+employeeId);
        return true;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Role> get() throws IOException {

        return dao.get();
    }

    @RequestMapping(value = "/{roleId}",method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String roleId) throws IOException {
        return dao.delete(roleId);
    }
}