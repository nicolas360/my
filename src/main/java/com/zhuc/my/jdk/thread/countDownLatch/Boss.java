package com.zhuc.my.jdk.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Boss implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Boss.class);

	private CountDownLatch downLatch;

	public Boss(CountDownLatch downLatch) {
		this.downLatch = downLatch;
	}

	public void run() {
		logger.debug("老板正在等所有的工人干完活......");
		try {
			this.downLatch.await();
		} catch (InterruptedException e) {
			logger.error("", e);
		}
		logger.debug("工人活都干完了，老板开始检查了！");
	}

}
