package com.brevitaz.dao;

import com.brevitaz.model.Employee;
import com.brevitaz.model.Right;
import com.brevitaz.model.Role;
import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.io.IOException;
import java.util.List;

@RunWith(RandomizedRunner.class)
@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
@SpringBootTest
public class RightDaoTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private RightDao rightDao;

    @Test
    public void createTest(){
        Right right = new Right();
        right.setId("1");
        right.setName("mnop");
        rightDao.insert(right);
        Right right1 = rightDao.getById("1");
        Assert.assertEquals(right.getName(),right1.getName());
        rightDao.delete("1");
    }

    @Test
    public void getAllTest(){
        Right right = new Right();
        right.setId("1");
        right.setName("mnop");
        rightDao.insert(right);

        Right right1 = new Right();
        right1.setId("2");
        right1.setName("mnop");
        rightDao.insert(right1);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Right> rights = rightDao.getAll();
        int size = rights.size();
        Assert.assertEquals(2,size);
        Assert.assertNotNull(rights);

        rightDao.delete("1");
        rightDao.delete("2");
    }

    @Test
    public void updateTest(){
        Right right = new Right();
        right.setId("1");
        right.setName("mnop");
        rightDao.insert(right);

        Right right1 = new Right();
        right1.setName("abc");
        rightDao.update(right1,"1");
        Right right2=rightDao.getById("1");
        Assert.assertEquals(right1.getName(),right2.getName());
        rightDao.delete("1");
    }

    @Test
    public void deleteTest(){
        Right right = new Right();
        right.setId("1");
        right.setName("mnop");
        rightDao.insert(right);
        rightDao.delete("1");
        Right right1=rightDao.getById("1");
        Assert.assertNull(right1);
    }

    @Test
    public void getByIdTest(){
        Right right = new Right();
        right.setId("1");
        right.setName("mnop");
        rightDao.insert(right);
        Right right1 = rightDao.getById("2");
        Assert.assertNotNull(right);
        Assert.assertNotEquals(true,right1);
        rightDao.delete("1");
    }
}
