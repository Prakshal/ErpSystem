package com.brevitaz.dao;

import com.brevitaz.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeDaoTest
{
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private Employee employee;

    @Test
    public void insertTest() throws IOException {
        System.out.println(employee);
        employee.setEmployeeId("1");
        employee.setFirstName("Prakshal");
        employee.setLastName("Doshi");

        Assert.assertTrue(employeeDao.insert(employee));

    }
}
