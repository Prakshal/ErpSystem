package com.brevitaz.dao;

import com.brevitaz.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;


public interface EmployeeDao
{
    public boolean insert(Employee employee) throws IOException;
    public List<Employee> getAll() throws IOException;
    public boolean update(Employee employee,String id) throws IOException;
    public boolean delete(String id) throws IOException;
    public Employee getById(String id) throws IOException;
}
