package com.zhuc.my.webservice.jaxws.t1;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding
public interface IHelloService {

	@WebMethod
	String sayHello(String name);

	@WebMethod
	String sayException(User user) throws Exception;

	@WebMethod
	void add(List<User> list);
}
