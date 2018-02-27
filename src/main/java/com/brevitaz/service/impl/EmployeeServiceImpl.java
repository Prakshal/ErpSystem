package com.brevitaz.service.impl;

import com.brevitaz.dao.EmployeeDao;
import com.brevitaz.model.Employee;
import com.brevitaz.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public boolean create(Employee employee) throws IOException {
        return employeeDao.insert(employee);
    }

    @Override
    public List<Employee> getAll() throws IOException {
        return employeeDao.getAll();
    }

    @Override
    public boolean update(Employee employee, String id) throws IOException {
        return employeeDao.update(employee,id);
    }

    @Override
    public boolean delete(String id) throws IOException {
        return employeeDao.delete(id);
    }

    @Override
    public Employee getById(String id) throws IOException {
        return employeeDao.getById(id);
    }

    @Override
    public Employee getByUsername(String username, String password) throws IOException {
        return employeeDao.getByUsername(username,password);
    }
}
