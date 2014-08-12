package com.zhuc.my.webservice.jaxws.t1;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceContext;

/**
 * @author zhuc
 * @version 2013-2-28 下午8:41:38
 */
@WebService(endpointInterface = "com.zhuc.my.webservice.jaxws.t1.IHelloService")
@SOAPBinding(style = Style.DOCUMENT)
//默认 DOCUMENT
//@SOAPBinding(style = Style.RPC)
public class HelloServiceImpl implements IHelloService {

	@Resource
	private WebServiceContext ws;

	/**
	 * @param name
	 * @return
	 */
	@Override
	@WebMethod
	public String sayHello(@WebParam(name = "userName") String name) {
		System.out.println(ws.getMessageContext().values());
		System.out.println("服务器端收到:" + name);
		return "你好:" + name;
	}

	/**
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@Override
	@WebMethod
	public String sayException(User user) throws Exception {
		System.out.println("收到异常:" + user);
		return "exception:" + user;
	}

	@Override
	@WebMethod
	public void add(List<User> list) {
		System.out.println(list);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/HelloService", new HelloServiceImpl());
		System.out.println("publish webservice successful");
	}
}
