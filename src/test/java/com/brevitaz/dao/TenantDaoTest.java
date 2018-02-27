package com.brevitaz.dao;

import com.brevitaz.model.Address;
import com.brevitaz.model.Tenant;
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
public class TenantDaoTest {

    @Autowired
    TenantDao tenantDao;

    @Test
    public  void create(){
        Tenant tenant = new Tenant();
        tenant.setId("1");
        tenant.setName("abc");
        tenantDao.insert(tenant);
        Tenant tenant1 = tenantDao.getById("1");
        Assert.assertEquals(tenant1.getName(),tenant.getName());
        tenantDao.delete("1");
    }

    @Test
    public void getAll(){
        Tenant tenant = new Tenant();
        tenant.setId("1");
        tenant.setName("abc");
        tenantDao.insert(tenant);

        Tenant tenant1 = new Tenant();
        tenant1.setId("2");
        tenant1.setName("mno");
        tenantDao.insert(tenant);

        List<Tenant> tenants = tenantDao.getAll();
        int size = tenants.size();
        Assert.assertEquals(2,size);
        tenantDao.delete("1");
        tenantDao.delete("2");
    }

    @Test
    public void getById(){
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
    public void update(){
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
    public void getByName(){
        Tenant tenant = new Tenant();
        tenant.setId("1");
        tenant.setName("abc");
        tenantDao.insert(tenant);

        Tenant tenant1 = new Tenant();
        tenant1.setId("2");
        tenant1.setName("abc");
        tenantDao.insert(tenant);

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
    public void delete(){
        Tenant tenant = new Tenant();
        tenant.setId("1");
        tenant.setName("abc");
        tenantDao.insert(tenant);
        tenantDao.delete("1");
        Tenant tenant1=tenantDao.getById("1");
        Assert.assertNotEquals(true,tenant1);
    }
}
