package com.zhuc.my.memcached.xmemcached;

import net.rubyeye.xmemcached.MemcachedClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/memcached/applicationContext-memcached.xml" })
public class SpringTest {

	private static final Logger logger = LoggerFactory.getLogger(SpringTest.class);

	@Autowired
	private MemcachedClient memcachedClient;

	@Test
	public void test() {
		try {
			System.out.println(memcachedClient);

			System.out.println(memcachedClient.get("hello"));
			memcachedClient.set("hello", 5, "zhuc"); // 超时时间, 5代表5秒后key失效, 0代表无限
			System.out.println(memcachedClient.get("hello"));
			Thread.sleep(5000l);
			System.out.println(memcachedClient.get("hello"));

			System.out.println(memcachedClient.delete("hello"));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
