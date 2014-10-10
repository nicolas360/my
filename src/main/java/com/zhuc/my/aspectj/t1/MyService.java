package com.zhuc.my.aspectj.t1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	private static final Logger logger = LoggerFactory.getLogger(MyService.class);

	@Log(module = "用户管理", function = "新增")
	public Integer saveUser(String name) {
		logger.debug("saveUser: " + name);

		return 2;
	}
}
