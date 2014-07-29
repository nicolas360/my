package com.zhuc.my.mongodb.spring.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "global")
public class Global extends MongoModel {

	@Indexed
	private Long gserverId;

	@Indexed
	private String serialNum;

	private long time = System.currentTimeMillis();

	/**
	 * @return the gserverId
	 */
	public Long getGserverId() {
		return gserverId;
	}

	/**
	 * @return the serialNum
	 */
	public String getSerialNum() {
		return serialNum;
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param gserverId the gserverId to set
	 */
	public void setGserverId(Long gserverId) {
		this.gserverId = gserverId;
	}

	/**
	 * @param serialNum the serialNum to set
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

}
