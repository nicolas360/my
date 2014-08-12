package com.zhuc.my.httpclient.t3;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author zhuc
 * @create 2013-6-19 下午1:23:19
 */
public class My {

	private String time;
	private String count;
	private String status;

	private List<LonLat> list = Lists.newArrayList();

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the list
	 */
	public List<LonLat> getList() {
		return list;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<LonLat> list) {
		this.list = list;
	}

}
