package com.zhuc.my.aspectj.t2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService2 {

	private static final Logger logger = LoggerFactory.getLogger(MyService2.class);

	public Integer saveUser(String name) {
		logger.debug("saveUser: " + name);

		return 3;
	}
}
