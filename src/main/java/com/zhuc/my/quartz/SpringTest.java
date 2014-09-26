package com.zhuc.my.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @version		2014-9-25 下午2:20:21
 * @author		zhuc
 */
public class SpringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 使用上面这行时, pom中只含quartz 1.8.x的坐标
		//		ApplicationContext ac = new ClassPathXmlApplicationContext("/com/zhuc/my/quartz/applicationContext.xml",
		//				"/quartz/scheduler1.8.x.spring.xml");

		// 使用下面这行时, pom中只含quartz 2.x的坐标
		ApplicationContext ac = new ClassPathXmlApplicationContext("/com/zhuc/my/quartz/applicationContext.xml",
				"/quartz/scheduler2.x.spring.xml");
		System.out.println(ac);
	}

}
