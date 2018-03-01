package com.brevitaz.dao;

import com.brevitaz.model.Employee;
import com.brevitaz.model.Right;
import com.brevitaz.model.Role;
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
public class RightDaoTest {

    @Autowired
    private RightDao rightDao;

    @Test
    public void create(){
        Right right = new Right();
        right.setId("1");
        right.setName("mnop");
        rightDao.insert(right);
        Right right1 = rightDao.getById("1");
        Assert.assertEquals(right.getName(),right1.getName());
        rightDao.delete("1");
    }

    @Test
    public void getAll(){
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
    public void update(){
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
    public void delete(){
        Right right = new Right();
        right.setId("1");
        right.setName("mnop");
        rightDao.insert(right);
        rightDao.delete("1");
        Right right1=rightDao.getById("1");
        Assert.assertNotEquals(true,right1);
    }

    @Test
    public void getById(){
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
