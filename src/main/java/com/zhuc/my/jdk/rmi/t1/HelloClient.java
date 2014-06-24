package com.zhuc.my.jdk.rmi.t1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: leizhimin Date: 2008-8-7 22:21:07
 * 客户端测试，在客户端调用远程对象上的远程方法，并返回结果。
 */
public class HelloClient {

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			// 在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法
			IHello rhello = (IHello) Naming.lookup("rmi://localhost:8888/RHello");
			System.out.println(rhello.helloWorld());
			System.out.println(rhello.sayHelloToSomeBody("熔岩"));

			Car c = rhello.getCar();
			System.out.println(c.getName());
			System.out.println(c.getCapacity());

			List<String> list = rhello.getList();
			for (String string : list) {
				System.out.println(string);
			}
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}