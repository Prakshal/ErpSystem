package com.brevitaz.dao;

import com.brevitaz.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeDaoTest {

    @Autowired
    EmployeeDao employeeDao ;



    @Test
    public void createTest(){
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("abc");
        employee.setLastName("pqr");
        employee.setPassword("abcd");
        employeeDao.insert(employee);
        Employee employee1=employeeDao.getById("1");
        Assert.assertEquals(employee1.getFirstName(),employee.getFirstName());
        Assert.assertEquals(employee1.getLastName(),employee.getLastName());

        employeeDao.delete("1");
    }

    @Test
    public void getAllTest(){

        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("abc");
        employee.setLastName("pqr");
        employee.setPassword("abcd");

        Employee employee1 = new Employee();
        employee1.setId("2");
        employee1.setFirstName("mno");
        employee1.setLastName("abc");
        employee1.setPassword("abcd");

        Employee employee2 = new Employee();
        employee2.setId("3");
        employee2.setFirstName("mno");
        employee2.setLastName("abc");
        employee2.setPassword("abcd");

        employeeDao.insert(employee);
        employeeDao.insert(employee1);
        employeeDao.insert(employee2);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Employee> employees = employeeDao.getAll();
        int size = employees.size();
        Assert.assertEquals(3,size);

        employeeDao.delete("1");
        employeeDao.delete("2");
        employeeDao.delete("3");
       }

    @Test
    public void getTest(){

        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("abc");
        employee.setLastName("pqr");
        employee.setPassword("abcd");
        employeeDao.insert(employee);
        Employee employee1=employeeDao.getById("1");
        Assert.assertEquals(employee1.getFirstName(),employee.getFirstName());
        employeeDao.delete("1");
    }

    @Test
    public void updateTest(){
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("abc");
        employee.setLastName("pqr");
        employee.setPassword("abcd");
        employeeDao.insert(employee);
        Employee employee2 = new Employee();
        employee2.setFirstName("abcd");
        employee2.setEmailId("majhgdj@gmaol.com");
        employeeDao.update(employee2,"1");
        Employee employee1=employeeDao.getById("1");
        Assert.assertEquals(employee1.getEmailId(),employee2.getEmailId());
        employeeDao.delete("1");

    }

    @Test
    public void deleteTest()
    {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("abc");
        employee.setLastName("pqr");
        employee.setPassword("abcd");
        employeeDao.insert(employee);
        employeeDao.delete("1");
        Employee employee1=employeeDao.getById("1");
        Assert.assertNotEquals(true,employee1);
    }
}
