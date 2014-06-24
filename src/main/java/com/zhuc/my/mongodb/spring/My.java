package com.zhuc.my.mongodb.spring;

import org.springframework.stereotype.Component;

@Component("my")
public class My {

	private String name = "zhuc";

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

}
