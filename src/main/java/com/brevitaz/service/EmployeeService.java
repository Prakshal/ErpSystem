package com.brevitaz.service;

import com.brevitaz.model.Employee;


import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    public boolean create(Employee employee);
    public List<Employee> getAll();
    public boolean update(Employee employee,String id);
    public boolean delete(String id);
    public Employee getById(String id);
    public Employee getByUsernameAndPassword(String username, String password);
}