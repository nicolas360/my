package com.zhuc.my.jdk.random;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public class Test1 {

	private static final Logger logger = LoggerFactory.getLogger(Test1.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long now = System.nanoTime();
		Random r = new Random();
		for (int i = 0; i < 100000; i++) {
			r.nextInt();
		}
		System.out.println(System.nanoTime() - now + "ns");

		now = System.nanoTime();
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		for (int i = 0; i < 100000; i++) {
			tlr.nextInt();
		}
		System.out.println(System.nanoTime() - now + "ns");

		// ThreadLocalRandom的耗时明显少于Random, 推荐

		Preconditions.checkArgument(ThreadLocalRandom.current() == ThreadLocalRandom.current(), "error");
		Assert.assertSame(ThreadLocalRandom.current(), ThreadLocalRandom.current());

		// ThreadLocalRandom.current()在单线程中是同一个实例, 但在多线程中是不同的

		UUID uuid = new UUID(ThreadLocalRandom.current().nextLong(), ThreadLocalRandom.current().nextLong());
		System.out.println(uuid);

		System.out.println(ThreadLocalRandom.current().nextLong());

		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 5; i++) {
			service.execute(new Runnable() {

				@Override
				public void run() {
					logger.debug("" + ThreadLocalRandom.current());
				}
			});
		}

		service.shutdown();
	}

}
