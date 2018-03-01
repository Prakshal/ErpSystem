package com.brevitaz.dao;

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
public class RoleDaoTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private RoleDao roleDao;

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

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
