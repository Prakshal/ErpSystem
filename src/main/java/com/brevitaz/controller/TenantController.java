package com.brevitaz.controller;

import com.brevitaz.model.Tenant;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tenant")
public class TenantController {
    private List<Tenant> tenants =new ArrayList<>();

    @RequestMapping(method = {RequestMethod.POST})
    public boolean create(@RequestBody Tenant tenant)
    {
        System.out.println("Tenant create Successfully");
        return true;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Tenant> get()
    {
        System.out.println("Get all tenant details");
        return tenants;
    }

    @RequestMapping(value = "/{tenantId}",method = {RequestMethod.GET})
    public Tenant getById(@PathVariable String tenantId)
    {
        System.out.println("Tenant get by id");
        return new Tenant();
    }

    @RequestMapping(value = "/{tenantId}",method = {RequestMethod.PUT})
    public boolean update(@RequestBody Tenant tenant,@PathVariable String tenantId)
    {
        System.out.println("Tenant updated");
        return true;
    }

    @RequestMapping(value = "/{tenantId}",method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String tenantId)
    {
        System.out.println("Tenant deleted");
        return true;
    }

    @RequestMapping(value = "/name/{tenantName}",method = {RequestMethod.GET})
    public Tenant getByName(@PathVariable String tenantName)
    {
        System.out.println("Tenant get by name");
        return new Tenant();
    }
}
