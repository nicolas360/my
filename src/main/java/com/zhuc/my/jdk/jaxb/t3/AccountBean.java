package com.zhuc.my.jdk.jaxb.t3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author zhuc
 * @version 2012-11-20 上午9:49:31
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "account")
@XmlType(propOrder = { "id", "version", "email", "address", "name", "birthday" })
public class AccountBean {
	@XmlAttribute(name = "number")
	private int id;
	@XmlAttribute(name = "version")
	private String version;
	@XmlElement
	private String name;
	@XmlElement
	private String email;
	@XmlElement
	private String address;
	@XmlElement
	private Birthday birthday;

	/**
	 * @return the version
	 */

	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	public Birthday getBirthday() {
		return birthday;
	}

	public void setBirthday(Birthday birthday) {
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return this.name + "#" + this.id + "#" + this.address + "#" + this.birthday + "#" + this.email;
	}
}