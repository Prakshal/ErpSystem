package com.brevitaz.dao;

import com.brevitaz.model.Role;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface RoleDao {
    public boolean create(Role role);
    public List<Role> getAll();
    public Role getById(String id);
    public boolean delete(String id);
}
