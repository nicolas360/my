package com.zhuc.my.netty.t3;

import java.io.Serializable;

/**
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.it165.net
 */
public class Command implements Serializable {

	private static final long serialVersionUID = 7590999461767050471L;

	private String actionName;

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

}