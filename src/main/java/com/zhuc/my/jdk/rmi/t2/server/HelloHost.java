package com.zhuc.my.jdk.rmi.t2.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhuc
 * @create 2013-9-6 下午2:27:50
 */
public class HelloHost {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"com/zhuc/my/jdk/rmi/t2/server/rmi_server_context.xml");
		System.out.println("RMI服务伴随Spring的启动而启动了.....");
	}
}