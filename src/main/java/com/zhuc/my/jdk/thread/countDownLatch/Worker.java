package com.zhuc.my.jdk.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Worker implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Worker.class);

	private CountDownLatch downLatch;
	private String name;

	public Worker(CountDownLatch downLatch, String name) {
		this.downLatch = downLatch;
		this.name = name;
	}

	public void run() {
		this.doWork();
		try {
			TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
		} catch (InterruptedException e) {
			logger.error("", e);
		}
		logger.debug(this.name + "活干完了！");
		this.downLatch.countDown();

	}

	private void doWork() {
		logger.debug(this.name + "正在干活!");
	}

}
