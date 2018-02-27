package com.brevitaz.dao;

import com.brevitaz.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeDaoTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void createTest(){
        Employee employee = new Employee();
        employee.setId("2");
        employee.setFirstName("mnwruierjkewj");
        employee.setLastName("pqrst");

        boolean status = employeeDao.insert(employee);
        Assert.assertEquals(true,status);
    }

    @Test
    public void getAllTest(){
        List<Employee> get = employeeDao.getAll();
        int size = get.size();
        Assert.assertEquals(1,size);
       }

    @Test
    public void get(){
        Employee employee = employeeDao.getById("1");
        Assert.assertNotNull(employee);
    }

    @Test
    public void update(){
        Employee employee = new Employee();
        employee.setFirstName("Anoushka");
        employee.setEmailId("abcd@gmail.com");
        boolean status = employeeDao.update(employee,"1");
        Assert.assertEquals(true,status);
    }

    @Test
    public void delete(){
        boolean status=employeeDao.delete("1");
        Assert.assertEquals(true,status);
    }
}
