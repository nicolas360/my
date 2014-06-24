package com.zhuc.my.jdk.jaxb.t3;

import javax.xml.bind.annotation.XmlElement;

public class MapElements {
	@XmlElement
	public String key;
	@XmlElement
	public AccountBean value;

	@SuppressWarnings("unused")
	private MapElements() {

	}

	// Required by JAXB
	public MapElements(String key, AccountBean value) {
		this.key = key;
		this.value = value;
	}
}