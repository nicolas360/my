package com.zhuc.my.mina.t3;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5596598780712169145L;

	private Long id;

	private String name;

	private Integer age;

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
	 * @return the age
	 */
	public Integer getAge() {
		return age;
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

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	public User() {
		super();
	}

	public User(Long id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
