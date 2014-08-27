package com.zhuc.my.mongodb.spring.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;

/**
 * mongodb抽象Entity类
 * @author		zhuc
 * @create		2014-1-17 上午9:34:50
 */
public class MongoModel {

	private ObjectId _id;

	/**
	 * @return the _id
	 */
	public ObjectId get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
