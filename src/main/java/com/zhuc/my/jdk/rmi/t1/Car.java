package com.zhuc.my.jdk.rmi.t1;

import java.io.Serializable;

/**
 * @author zhuc
 * @version 2012-5-18 上午10:02:54
 */
public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3520380234024753698L;

	private String name;

	private transient Integer capacity;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the capacity
	 */
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

}
