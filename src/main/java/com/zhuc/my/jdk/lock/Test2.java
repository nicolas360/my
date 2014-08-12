package com.zhuc.my.jdk.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test2 {

	private static final ReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 3; i++) {
			// 读写锁 写写互斥
			service.execute(new MyWrite(lock));
		}

		for (int i = 0; i < 3; i++) {
			// 读写锁 读读不互斥
			service.execute(new MyRead(lock));
		}

		// 读写锁 读写互斥

		service.shutdown();
	}

}

class MyWrite implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(MyWrite.class);

	private ReadWriteLock lock;

	public MyWrite() {
		super();
	}

	public MyWrite(ReadWriteLock lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		lock.writeLock().lock();

		logger.info("write");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.writeLock().unlock();
	}

}

class MyRead implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(MyRead.class);

	private ReadWriteLock lock;

	public MyRead() {
		super();
	}

	public MyRead(ReadWriteLock lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		lock.readLock().lock();

		logger.info("read");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.readLock().unlock();
	}

}
