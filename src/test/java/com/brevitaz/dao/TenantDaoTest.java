package com.brevitaz.dao;

import com.brevitaz.model.Address;
import com.brevitaz.model.Tenant;
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
public class TenantDaoTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    @Autowired
    private TenantDao tenantDao;

    @Test
    public  void createTest(){
        Tenant tenant = new Tenant();
        tenant.setId("1");
        tenant.setName("abc");
        tenantDao.insert(tenant);
        Tenant tenant1 = tenantDao.getById("1");
        Assert.assertEquals(tenant1.getName(),tenant.getName());
        tenantDao.delete("1");
    }

    @Test
    public void getAllTest(){
        Tenant tenant = new Tenant();
        tenant.setId("1");
        tenant.setName("abc");
        tenantDao.insert(tenant);

        Tenant tenant1 = new Tenant();
        tenant1.setId("2");
        tenant1.setName("mno");
        tenantDao.insert(tenant1);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Tenant> tenants = tenantDao.getAll();
        int size = tenants.size();
        Assert.assertEquals(2,size);
        tenantDao.delete("1");
        tenantDao.delete("2");
    }

    @Test
    public void getByIdTest(){
        Tenant tenant = new Tenant();
        tenant.setId("1");
        tenant.setName("abc");
        tenantDao.insert(tenant);
        Tenant tenant1 = tenantDao.getById("1");
        Assert.assertNotNull(tenant1);
        Assert.assertEquals(tenant1.getName(),tenant.getName());
        tenantDao.delete("1");
    }

    @Test
    public void updateTest(){
        Tenant tenant = new Tenant();
        tenant.setId("1");
        tenant.setName("abc");
        tenantDao.insert(tenant);

        Tenant tenant1 = new Tenant();
        tenant1.setName("iwwudya");
        Address address=new Address();
        address.setCity("Ahmedabad");
        tenant1.setAddress(address);
        tenantDao.update(tenant1,"1");

        Tenant tenant2=tenantDao.getById("1");
        //Assert.assertEquals(tenant2.getAddress(),tenant1.getAddress());
        Assert.assertEquals(tenant2.getName(),tenant1.getName());
        tenantDao.delete("1");
    }

    @Test
    public void getByNameTest(){
        Tenant tenant = new Tenant();
        tenant.setId("1");
        tenant.setName("abc");
        tenantDao.insert(tenant);

        Tenant tenant1 = new Tenant();
        tenant1.setId("2");
        tenant1.setName("abc");
        tenantDao.insert(tenant1);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Tenant> tenants = tenantDao.getByName("abc");
        int size = tenants.size();
        Assert.assertEquals(2,size);
        tenantDao.delete("1");
        tenantDao.delete("2");
    }

    @Test
    public void deleteTest(){
        Tenant tenant = new Tenant();
        tenant.setId("1");
        tenant.setName("abc");
        tenantDao.insert(tenant);
        tenantDao.delete("1");
        Tenant tenant1=tenantDao.getById("1");
        Assert.assertNull(tenant1);
    }
}
