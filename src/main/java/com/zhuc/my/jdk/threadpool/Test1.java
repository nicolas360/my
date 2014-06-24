package com.zhuc.my.jdk.threadpool;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		//		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(3000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(111);
			}
		});

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
		for (int i = 0; i < 4; i++) {
			scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ":" + new Date());
				}
			}, 3, 5, TimeUnit.SECONDS);
		}

		//		service.shutdown();
		//		scheduledExecutorService.shutdown();

		System.out.println(Arrays.toString(DigestUtils.md5("123")));
	}
}
