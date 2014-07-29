package com.zhuc.my.mongodb.spring.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class MongoDao<T> {

	private static final Logger logger = LoggerFactory.getLogger(MongoDao.class);

	@Autowired
	private MongoTemplate mt;

	/**
	 * saveOrUpdate
	 * @param t
	 */
	public void saveOrUpdate(T t) {
		mt.save(t);
	}

	/**
	 * insert操作
	 * @param t
	 */
	public void save(T t) {
		mt.insert(t);
	}

	/**
	 * 批量insert
	 * @param list
	 */
	public void save(List<T> list) {
		if (!list.isEmpty()) {
			mt.insert(list, list.get(0).getClass());
		}
	}

	public void remove(T t) {
		mt.remove(t);
	}

	/**
	 * @return the mt
	 */
	public MongoTemplate getMt() {
		return mt;
	}

}
