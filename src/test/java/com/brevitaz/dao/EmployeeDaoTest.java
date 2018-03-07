package com.brevitaz.dao;

import com.brevitaz.model.Employee;
import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.util.List;


@RunWith(RandomizedRunner.class)
@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
@SpringBootTest
public class EmployeeDaoTest {


    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE= new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void createTest(){
        Employee employee = new Employee();
        employee.setId("brz123");
        employee.setFirstName("abc");
        employee.setLastName("pqr");
        employee.setPassword("abcd");
        employeeDao.insert(employee);
        Employee employee1=employeeDao.getById("brz123");
        Assert.assertEquals(employee1.getFirstName(),employee.getFirstName());
        Assert.assertEquals(employee1.getLastName(),employee.getLastName());

        employeeDao.delete("brz123");
    }

    @Test
    public void getAllTest(){

        Employee employee = new Employee();
        employee.setId("brz123");
        employee.setFirstName("abc");
        employee.setLastName("pqr");
        employee.setPassword("abcd");

        Employee employee1 = new Employee();
        employee1.setId("brz124");
        employee1.setFirstName("mno");
        employee1.setLastName("abc");
        employee1.setPassword("abcd");

        Employee employee2 = new Employee();
        employee2.setId("brz125");
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

        employeeDao.delete("brz123");
        employeeDao.delete("brz124");
        employeeDao.delete("brz125");
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
        Assert.assertNull(employee1);
    }
}
