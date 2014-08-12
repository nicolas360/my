package com.zhuc.my.jdk.queue;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue是这样 一种阻塞队列,其中每个 put 必须等待一个 take,反之亦然。同步队列没有任何内部容量,甚至连一个队列的容量都没有
 * @author zhuc
 * @create 2013-10-29 下午4:31:43
 */
public class SynchronousQueueTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		SynchronousQueue<String> queue = new SynchronousQueue<String>();

		new Thread(new Put(queue)).start();
		new Thread(new Take(queue)).start();

	}

}

class Put implements Runnable {
	private final SynchronousQueue<String> queue;

	public Put(SynchronousQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Integer n = new Random().nextInt(100);
				System.out.println("put: " + n);
				queue.put(n + "");

				Thread.sleep(3000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class Take implements Runnable {
	private final SynchronousQueue<String> queue;

	public Take(SynchronousQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("take: " + queue.take());

				Thread.sleep(3000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}