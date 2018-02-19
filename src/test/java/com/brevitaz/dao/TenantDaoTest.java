package com.brevitaz.dao;

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
    public  void create() throws IOException {
        Tenant tenant = new Tenant();
        tenant.setId("3");
        tenant.setName("abc");

        boolean data = tenantDao.insert(tenant);
        Assert.assertEquals(true,data);
    }

    @Test
    public void getAll() throws IOException {
        List<Tenant> tenants = tenantDao.tenants();
        int size = tenants.size();
        Assert.assertEquals(1,size);
    }

    @Test
    public void getById() throws IOException {
        Tenant tenant = tenantDao.getById("1");
        Assert.assertNotNull(tenant);
    }

    @Test
    public void update() throws IOException {
        Tenant tenant = new Tenant();
        tenant.setName("iwwudya");
        boolean data=tenantDao.update(tenant,"1");
        Assert.assertEquals(true,data);
    }

    @Test
    public void getByName() throws IOException {
        List<Tenant> tenants = tenantDao.getByName("abc");
        int size = tenants.size();
        Assert.assertEquals(2,size);
    }

    @Test
    public void delete() throws IOException {
        boolean data = tenantDao.delete("1");
        Assert.assertEquals(true,data);
    }
}
