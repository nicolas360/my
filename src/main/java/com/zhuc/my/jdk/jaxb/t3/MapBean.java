package com.zhuc.my.jdk.jaxb.t3;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class MapBean {
	private Map<String, AccountBean> map;

	@XmlJavaTypeAdapter(MapAdapter.class)
	public Map<String, AccountBean> getMap() {
		return map;
	}

	public void setMap(Map<String, AccountBean> map) {
		this.map = map;
	}
}