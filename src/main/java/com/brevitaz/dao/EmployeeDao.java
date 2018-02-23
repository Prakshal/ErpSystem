package com.brevitaz.dao;

import com.brevitaz.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;


public interface EmployeeDao
{
    public boolean insert(Employee employee);
    public List<Employee> getAll();
    public boolean update(Employee employee,String id);
    public boolean delete(String id);
    public Employee getById(String id);
    public Employee getByUsernameAndPassword(String username, String password);
}
