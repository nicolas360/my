package com.zhuc.my.jdk.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch的一个非常典型的应用场景是：有一个任务想要往下执行，但必须要等到其他的任务执行完毕后才可以继续往下执行。假如我们这个想要继续往下执行的任务调用一个CountDownLatch对象的await()方法，其他的任务执行完自己的任务后调用同一个CountDownLatch对象上的countDown()方法，这个调用await()方法的任务将一直阻塞等待，直到这个CountDownLatch对象的计数值减到0为止
 * @version		2014-8-27 上午10:43:04
 * @author		zhuc
 */
public class CountDownLatchDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();

		CountDownLatch latch = new CountDownLatch(3);

		executor.execute(new Worker(latch, "张三"));
		executor.execute(new Worker(latch, "李四"));
		executor.execute(new Worker(latch, "王五"));
		executor.execute(new Boss(latch));

		executor.shutdown();
	}

}
