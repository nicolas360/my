package com.zhuc.my.quartz;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * spring task 不需要quartz
 * 配置请看当前目录下applicationContext.xml
 */
@Component("task")
public class Task {

	private static final Logger logger = LoggerFactory.getLogger(Task.class);

	@Scheduled(cron = "*/5 * * * * ?")
	public void test() {
		logger.debug(new Date().toString());
	}
}
