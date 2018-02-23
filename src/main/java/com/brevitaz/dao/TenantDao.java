package com.brevitaz.dao;

import com.brevitaz.model.Tenant;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface TenantDao
{
    public boolean insert(Tenant tenant);
    public List<Tenant> getAll();
    public Tenant getById(String id);
    public boolean update(Tenant tenant,String id);
    public List<Tenant> getByName(String name);
    public boolean delete(String id);
}
