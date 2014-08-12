package com.zhuc.my.jdk.rmi.t1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
* Created by IntelliJ IDEA.
* User: leizhimin
* Date: 2008-8-7 21:50:02
* 定义一个远程接口，必须继承Remote接口，其中需要远程调用的方法必须抛出RemoteException异常
*/
public interface IHello extends Remote {

	/**
	 * @return
	 * @throws RemoteException
	 */
	public Car getCar() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	public List<String> getList() throws RemoteException;

	/**
	 * 简单的返回“Hello World！"字样
	 * @return 返回“Hello World！"字样
	 * @throws java.rmi.RemoteException
	 */
	public String helloWorld() throws RemoteException;

	/**
	 * 一个简单的业务方法，根据传入的人名返回相应的问候语
	 * @param someBodyName  人名
	 * @return 返回相应的问候语
	 * @throws java.rmi.RemoteException
	 */
	public String sayHelloToSomeBody(String someBodyName) throws RemoteException;
}