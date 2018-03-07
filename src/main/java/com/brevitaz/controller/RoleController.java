package com.brevitaz.controller;

import com.brevitaz.dao.RoleDao;
import com.brevitaz.model.Employee;
import com.brevitaz.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController {

    @Autowired
    private RoleDao roleDao;

    @PreAuthorize("hasAuthority('Role:CREATE')")
    @RequestMapping(method = {RequestMethod.POST})
    public boolean create(@RequestBody Role role){
        return roleDao.create(role);
    }

    @PreAuthorize("hasAuthority('Role:ASSIGN')")
    @RequestMapping(value="/{id}/assign/{employeeId}",method = {RequestMethod.POST})
    public boolean assign(@PathVariable String id,String employeeId)
    {
        return true;
    }

    @PreAuthorize("hasAuthority('Role:READ')")
    @RequestMapping(method = {RequestMethod.GET})
    public List<Role> getAll() {

        return roleDao.getAll();
    }

    @PreAuthorize("hasAuthority('Role:READ')")
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public Role getById(@PathVariable String id) {

        return roleDao.getById(id);
    }

    @PreAuthorize("hasAuthority('Role:DELETE')")
    @RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String id) {
        return roleDao.delete(id);
    }
}