package com.zhuc.my.mongodb.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(My.class);
		My m = (My) ac.getBean("my");
		My m2 = (My) ac.getBean("my");
		System.out.println(m);
		System.out.println(m2);
		System.out.println(m.getName());
	}

}
