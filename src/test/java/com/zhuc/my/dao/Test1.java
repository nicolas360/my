package com.zhuc.my.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhuc
 * @create 2013-6-7 下午2:26:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContext.xml" })
public class Test1 {

	/**
	 * 
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void add() {

	}
}
