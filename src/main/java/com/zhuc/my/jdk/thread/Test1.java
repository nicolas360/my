package com.zhuc.my.jdk.thread;

import java.util.concurrent.ExecutionException;

public class Test1 {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread a = new Thread(new A());
		Thread b = new Thread(new B());
		a.start();
		long now = System.currentTimeMillis();
		//		a.join();
		Thread.yield();
		System.out.println(System.currentTimeMillis() - now + "ms");
		b.start();
		b.join();
	}

}

class A implements Runnable {

	@Override
	public void run() {
		System.out.println(1);
		try {
			Thread.sleep(2000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class B implements Runnable {

	@Override
	public void run() {
		System.out.println(2);
		try {
			Thread.sleep(2000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}