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
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    private TenantDao tenantDao;

    @RequestMapping(method = {RequestMethod.POST})
    public boolean create(@RequestBody Tenant tenant) throws IOException {
        return tenantDao.insert(tenant);
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Tenant> get() throws IOException {
        return tenantDao.tenants();
    }

    @RequestMapping(value = "/{tenantId}",method = {RequestMethod.GET})
    public Tenant getById(@PathVariable String tenantId) throws IOException {
     return tenantDao.getById(tenantId);
    }

    @RequestMapping(value = "/{tenantId}",method = {RequestMethod.PUT})
    public boolean update(@RequestBody Tenant tenant,@PathVariable String tenantId) throws IOException {
        return tenantDao.update(tenant,tenantId);
    }

    @RequestMapping(value = "/{tenantId}",method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String tenantId) throws IOException {
        return tenantDao.delete(tenantId);
    }

    @RequestMapping(value = "/name/{tenantName}",method = {RequestMethod.GET})
    public List<Tenant> getByName(@PathVariable String tenantName) throws IOException {
        return tenantDao.getByName(tenantName);
    }
}