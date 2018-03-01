package com.brevitaz;

import com.brevitaz.dao.TestSuit;
import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(RandomizedRunner.class)
@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
@SpringBootTest
public class ErpSystemApplicationTests {

	@Test
	public void contextLoads() {
		Result result = JUnitCore.runClasses(TestSuit.class);
		for(Failure failure :result.getFailures())
		{
			System.out.println(failure.toString());
		}
		System.out.println(result.toString());
	}
}
