package com.zhuc.my.memcached.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhuc
 * @create 2013-6-21 下午1:22:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/memcached/applicationContext-memcached.xml" })
public class Test1 {

	@Autowired
	private StudentDao studentDao;

	/**
	 * 
	 */
	@Test
	public void save() {
		System.out.println(studentDao.get("200"));

		Student s = new Student("207", "我是spy", 20);
		studentDao.update(s);

		studentDao.delete("200");
	}
}
