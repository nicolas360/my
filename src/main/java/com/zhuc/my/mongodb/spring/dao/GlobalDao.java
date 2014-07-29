package com.zhuc.my.mongodb.spring.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.zhuc.my.mongodb.spring.model.Global;

@Repository("globalDao")
public class GlobalDao extends MongoDao<Global> {

	private static final Logger logger = LoggerFactory.getLogger(GlobalDao.class);

	private static final String COLLECTION_NAME = "global";

	public Global findByDeviceNo(String deviceNo) {
		return getMt().findOne(new Query(Criteria.where("serialNum").is(deviceNo)), Global.class);
	}

	public List<Global> findAll() {
		return getMt().findAll(Global.class);
	}

	public List<Global> findByDeviceNoList(List<String> deviceNoList) {
		return getMt().find(new Query(Criteria.where("serialNum").in(deviceNoList)), Global.class);
	}

}
