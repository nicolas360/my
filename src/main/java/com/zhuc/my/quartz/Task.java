package com.zhuc.my.quartz;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * spring task 不需要quartz
 * 配置请看当前目录下applicationContext.xml
 */
@Component("task")
public class Task {

	@Scheduled(cron = "*/5 * * * * ?")
	public void test() {
		System.out.println(new Date());
	}
}
