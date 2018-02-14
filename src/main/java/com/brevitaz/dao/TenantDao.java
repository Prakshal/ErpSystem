package com.brevitaz.dao;

import com.brevitaz.model.Tenant;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface TenantDao
{
    public boolean insert(Tenant tenant) throws IOException;
    public List<Tenant> tenants() throws IOException;
    public Tenant getById(String id) throws IOException;
    public boolean update(Tenant tenant,String id) throws IOException;
    public List<Tenant> getByName(String name) throws IOException;
    public boolean delete(String id) throws IOException;
}
