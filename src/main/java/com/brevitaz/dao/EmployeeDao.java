package com.brevitaz.dao;

import com.brevitaz.model.Employee;

import java.util.List;

public interface EmployeeDao
{
    public boolean insert(Employee employee);
    public List<Employee> getAll();
    public boolean update(Employee employee,String employeeId);
    public boolean delete(String employeeId);
    public Employee getById(String employeeId);
}
