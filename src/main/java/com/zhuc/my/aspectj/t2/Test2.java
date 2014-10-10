package com.zhuc.my.aspectj.t2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/com/zhuc/my/aspectj/t2/applicationContext.xml" })
public class Test2 {

	@Autowired
	private MyService2 myService;

	@Test
	public void main() {
		myService.saveUser("zhuc");
	}

}
