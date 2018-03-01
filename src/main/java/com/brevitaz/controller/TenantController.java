package com.brevitaz.controller;

import com.brevitaz.dao.EmployeeDao;
import com.brevitaz.dao.TenantDao;
import com.brevitaz.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/tenant")
public class TenantController {

    @Autowired
    private TenantDao tenantDao;

    @RequestMapping(method = {RequestMethod.POST})
    public boolean create(@RequestBody Tenant tenant){
        return tenantDao.insert(tenant);
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Tenant> getAll() {
        return tenantDao.getAll();
    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.GET})
    public Tenant getById(@PathVariable String id) {
     return tenantDao.getById(id);
    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
    public boolean update(@RequestBody Tenant tenant,@PathVariable String id) {
        return tenantDao.update(tenant,id);
    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String id) {
        return tenantDao.delete(id);
    }

    @RequestMapping(value = "/name/{name}",method = {RequestMethod.GET})
    public List<Tenant> getByName(@PathVariable String name) {
        return tenantDao.getByName(name);
    }
}