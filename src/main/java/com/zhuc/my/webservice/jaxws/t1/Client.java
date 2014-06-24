package com.zhuc.my.webservice.jaxws.t1;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.google.common.collect.ImmutableList;

public class Client {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8080/HelloService?wsdl");
		// 第一个参数是服务的URI   
		// 第二个参数是在WSDL发布的服务名  
		QName qName = new QName("http://t1.jaxws.webservice.my.zhuc.com/", "HelloServiceImplService");

		Service service = Service.create(url, qName);
		IHelloService hs = service.getPort(IHelloService.class);
		System.out.println(hs.sayHello("zhuc"));

		User user = new User();
		user.setName("zhuc-name");
		System.out.println(hs.sayException(user));

		ImmutableList<User> list = ImmutableList.of(new User("u1"), new User("u2"));
		hs.add(list);
	}

}
