package com.zhuc.my.jdk.jaxb.t1;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author zhuc
 * @version 2012-12-16 下午7:09:15
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "FATHER")
@XmlType(propOrder = { "id", "name", "son" })
public class Father {

	@XmlAttribute
	private Integer id;

	@XmlElement
	private String name;

	@XmlElementWrapper(name = "sons")
	@XmlElement(name = "son")
	private List<Son> son;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the son
	 */
	public List<Son> getSon() {
		return son;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param son the son to set
	 */
	public void setSon(List<Son> son) {
		this.son = son;
	}

	public Father(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Father() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Father [id=" + id + ", name=" + name + ", son=" + son + "]";
	}

}
