package com.brevitaz.service;

import com.brevitaz.model.Employee;

import java.io.IOException;

public interface EmployeeService
{
    boolean insert(Employee employee) throws IOException;
    Employee getById(String id) throws IOException;

}
