package com.zhuc.my.jdk.jaxb.t3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * @author zhuc
 * @version 2012-11-20 上午9:49:31
 */
@XmlRootElement(name = "account")
public class Account {
	private int id;
	private String name;
	private String email;
	private String address;
	private Birthday birthday;

	@XmlElement
	public Birthday getBirthday() {
		return birthday;
	}

	public void setBirthday(Birthday birthday) {
		this.birthday = birthday;
	}

	@XmlAttribute(name = "number")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return this.name + "#" + this.id + "#" + this.address + "#"
				+ this.birthday + "#" + this.email;
	}
}