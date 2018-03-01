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
    public boolean create(Employee employee){
        return employeeDao.insert(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Override
    public boolean update(Employee employee, String id){
        return employeeDao.update(employee,id);
    }

    @Override
    public boolean delete(String id){
        return employeeDao.delete(id);
    }

    @Override
    public Employee getById(String id){
        return employeeDao.getById(id);
    }

    @Override
    public Employee getByUsernameAndPassword(String username, String password){
        return employeeDao.getByUsernameAndPassword(username,password);
    }
}
