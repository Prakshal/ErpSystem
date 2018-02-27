package com.brevitaz.dao;

import com.brevitaz.model.Employee;
import com.brevitaz.model.Right;
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
    RightDao rightDao;

    @Test
    public void create() throws IOException {
        Right right = new Right();
        right.setId("2");
        right.setName("mnop");

        boolean status = rightDao.insert(right);
        Assert.assertEquals(true,status);
    }

    @Test
    public void getAll() throws IOException {
        List<Right> rights = rightDao.getAll();
        int size = rights.size();
        Assert.assertEquals(1,size);
    }

    @Test
    public void update() throws IOException {
        Right right = new Right();
        right.setName("abc");
        boolean status = rightDao.update(right,"1");
        Assert.assertEquals(true,status);
    }

    @Test
    public void delete() throws IOException {
        boolean status = rightDao.delete("1");
        Assert.assertEquals(true,status);
    }

    @Test
    public void getById() throws IOException {
        Right right = rightDao.getById("2");
        Assert.assertNotNull(right);
    }
}
