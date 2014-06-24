package com.zhuc.my.jdk.threadpool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import net.sf.ehcache.util.NamedThreadFactory;

public class Test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service1 = Executors.newFixedThreadPool(10);
		ExecutorService service2 = Executors.newFixedThreadPool(10, new DaemonThreadFactory());
		ExecutorService service3 = Executors.newFixedThreadPool(10, new MaxPriorityThreadFactory());
		ExecutorService service4 = Executors.newFixedThreadPool(10, new MinPriorityThreadFactory());
		ExecutorService service5 = Executors.newFixedThreadPool(10, new NamedThreadFactory("采集平台-client"));
		ExecutorService service6 = Executors.newCachedThreadPool(new NamedThreadFactory("hello"));

		//		for (int i = 0; i < 10; i++) {
		//			service1.execute(new My());
		//		}
		//		for (int i = 0; i < 10; i++) {
		//			service2.execute(new My());
		//		}
		//		for (int i = 0; i < 10; i++) {
		//			service3.execute(new My());
		//		}
		for (int i = 0; i < 20; i++) {
			service6.execute(new My());
		}

	}
}

class My implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ":" + new Date().toLocaleString());
	}

}

class DaemonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}

}

class MaxPriorityThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setPriority(Thread.MAX_PRIORITY);
		return t;
	}

}

class MinPriorityThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setPriority(Thread.MIN_PRIORITY);
		return t;
	}

}
