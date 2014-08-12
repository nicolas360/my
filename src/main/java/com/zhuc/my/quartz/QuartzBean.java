package com.zhuc.my.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("quartzBean")
public class QuartzBean {

	private static final Logger logger = LoggerFactory.getLogger(QuartzBean.class);

	public void start() {
		logger.debug("hello");
	}
}
