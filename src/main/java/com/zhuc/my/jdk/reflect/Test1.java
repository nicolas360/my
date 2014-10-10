package com.zhuc.my.jdk.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * 子类对象获取父类及子类字段通过反射设值
 * @version		2014-10-9 下午4:32:29
 * @author		zhuc
 */
public class Test1 {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, InstantiationException {
		//		Class<?> c = Class.forName("com.zhuc.my.jdk.reflect.You");
		Class<?> c = You.class;

		You y = (You) c.newInstance();
		for (; c != Object.class; c = c.getSuperclass()) {
			Field[] fs = c.getDeclaredFields();
			for (Field f : fs) {
				Method m = c.getMethod("set" + StringUtils.capitalize(f.getName()), f.getType());
				if (f.getType() == String.class) {
					m.invoke(y, "string" + new Random().nextInt());

				} else if (f.getType() == Integer.class) {
					m.invoke(y, new Random().nextInt());

				} else if (f.getType() == Long.class) {
					m.invoke(y, new Random().nextLong());

				}

			}

		}

		System.out.println(JSON.toJSONString(y));

	}

}

class My {
	private Long id;

	private String name;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}

class You extends My {

	private String hello;

	/**
	 * @return the hello
	 */
	public String getHello() {
		return hello;
	}

	/**
	 * @param hello the hello to set
	 */
	public void setHello(String hello) {
		this.hello = hello;
	}

}