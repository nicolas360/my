package com.zhuc.my.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * @author zhuc
 * @create 2013-6-8 上午11:07:35
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "/applicationContext.xml", "/scheduler.spring.xml" })
public class Test1 {

	private static final Logger logger = LoggerFactory.getLogger(Test1.class);

	@Test
	public void t1() {
		logger.error("test");

		try {
			String my = null;
			//			Assert.assertNotNull(my);

			//			org.springframework.util.Assert.notNull(my);

			String my1 = Preconditions.checkNotNull(my);
			System.out.println("my1: " + my1);

		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
