package com.zhuc.my.jdk.threadpool;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 5, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.DiscardPolicy());

		for (int i = 0; i < 3; i++) {
			executor.execute(new Runnable() {

				Logger logger = LoggerFactory.getLogger(getClass());

				@Override
				public void run() {
					logger.debug(new Date().toLocaleString());
				}
			});
		}

		executor.shutdown();
	}
}
