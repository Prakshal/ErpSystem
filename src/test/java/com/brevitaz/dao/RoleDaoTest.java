package com.brevitaz.dao;

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
public class RoleDaoTest {
    @Autowired
    private RoleDao roleDao;

    @Test
    public void insert() throws IOException {
        Role role = new Role();
        role.setId("2");
        role.setName("abckdjas");

        boolean status = roleDao.create(role);
        Assert.assertEquals(true,status);
    }

    @Test
    public void getAll() throws IOException {
        List<Role> roles=roleDao.get();
        int size = roles.size();
        Assert.assertEquals(1,size);
    }

    @Test
    public void delete() throws IOException {
        boolean status = roleDao.delete("1");
        Assert.assertEquals(true,status);
    }
}
