package com.brevitaz.dao;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(RandomizedRunner.class)
@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
@Suite.SuiteClasses({EmployeeDaoTest.class,RightDaoTest.class,RoleDaoTest.class,TenantDaoTest.class})
@SpringBootTest
public class TestSuit {
    public void message()
    {
        System.out.println("All test class are combine in test suite");
    }
}
