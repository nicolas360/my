package com.zhuc.my.jdk.rmi.t2.server;

public class HelloWorld implements IHelloWorld {

	@Override
	public String helloWorld() {
		return "Hello World!";
	}

	@Override
	public String sayHelloToSomeBody(String someBodyName) {
		return "Hello World!" + someBodyName;
	}

}