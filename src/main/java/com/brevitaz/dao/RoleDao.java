package com.brevitaz.dao;

import com.brevitaz.model.Role;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface RoleDao {
    public boolean create(Role role) throws IOException;
    public List<Role> get() throws IOException;
    public boolean delete(String roleId) throws IOException;
}
