package com.zhuc.my.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-27
 * Time: 下午8:46
 * To change this template use File | Settings | File Templates.
 */
public class SpringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//		ApplicationContext ac = new ClassPathXmlApplicationContext("/applicationContext.xml",
		//				"/quartz/scheduler1.8.x.spring.xml");
		ApplicationContext ac = new ClassPathXmlApplicationContext("/applicationContext.xml",
				"/quartz/scheduler2.x.spring.xml");
		System.out.println(ac);
	}

}
