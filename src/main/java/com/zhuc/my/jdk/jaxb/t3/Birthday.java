package com.zhuc.my.jdk.jaxb.t3;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhuc
 * @version 2012-11-20 上午9:41:45
 */
@XmlRootElement
public class Birthday {
	private String birthday;

	public Birthday(String birthday) {
		super();
		this.birthday = birthday;
	}

	public Birthday() {
		super();
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
