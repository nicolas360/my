package com.zhuc.my.jdk.rmi.t2.client;

import java.rmi.RemoteException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhuc.my.jdk.rmi.t2.server.IHelloWorld;

/**
 * @author zhuc
 * @create 2013-9-6 下午2:25:44
 */
public class HelloClient {

	/**
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"com/zhuc/my/jdk/rmi/t2/client/rmi_client_context.xml");
		IHelloWorld hs = (IHelloWorld) ctx.getBean("helloclient");
		System.out.println(hs.helloWorld());
		System.out.println(hs.sayHelloToSomeBody("Lavasoft"));
	}

}