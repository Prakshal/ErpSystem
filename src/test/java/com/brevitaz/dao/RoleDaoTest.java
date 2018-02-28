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
    RoleDao roleDao;

    @Test
    public void insertTest(){
        Role role = new Role();
        role.setId("1");
        role.setName("abckdjas");
        roleDao.create(role);
        Role role1=roleDao.getById("1");
        Assert.assertEquals(role1.getName(),role.getName());
        roleDao.delete("1");
    }

    @Test
    public void getAllTest(){
        Role role = new Role();
        role.setId("1");
        role.setName("abckdjas");
        roleDao.create(role);

        Role role1 = new Role();
        role1.setId("2");
        role1.setName("mno");
        roleDao.create(role1);

        List<Role> roles= roleDao.getAll();
        int size = roles.size();
        Assert.assertEquals(2,size);

        roleDao.delete("1");
        roleDao.delete("2");
    }

    @Test
    public void deleteTest(){
        Role role = new Role();
        role.setId("1");
        role.setName("abckdjas");
        roleDao.create(role);
        roleDao.delete("1");
        Role role1 = roleDao.getById("1");
        Assert.assertNull(role1);
    }

    @Test
    public void getByIdTest()
    {
        Role role = new Role();
        role.setId("1");
        role.setName("abckdjas");
        roleDao.create(role);
        Role role1 = roleDao.getById("1");
        Assert.assertNotNull(role1);
        Assert.assertEquals(role1.getName(),role.getName());
        roleDao.delete("1");
    }
}
