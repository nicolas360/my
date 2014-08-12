package com.zhuc.my.netty.netty4.t2;

import java.io.Serializable;

public class My implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2558526952196555902L;

	private String name;

	private Integer id;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public My(String name, Integer id) {
		super();
		this.name = name;
		this.id = id;
	}

	public My() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "My [name=" + name + ", id=" + id + "]";
	}

}
