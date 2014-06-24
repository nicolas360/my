package com.zhuc.my.guava;

/**
 * @author zhuc
 * @create 2013-4-7 下午1:50:53
 */
public class User {

	private String name;

	private Integer age;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @param name
	 * @param age
	 */
	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	/**
	 * 
	 */
	public User() {
		super();
	}

}
