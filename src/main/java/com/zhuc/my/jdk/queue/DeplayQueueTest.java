package com.zhuc.my.jdk.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2013-10-29 下午4:21:45
 */
public class DeplayQueueTest {

	private static final Logger logger = LoggerFactory.getLogger(DeplayQueueTest.class);

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		DelayQueue<DelayTask> queue = new DelayQueue<DelayTask>();
		for (int i = 0; i < 10; i++) {
			long delay = (long) (Math.random() * 20000);
			//			long delay = 2000l;
			queue.put(new DelayTask(delay, i));
		}

		//		queue.remove(new DelayTask(2000l, 7)); // 移除该元素,不管其是否过期

		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new Consumer(queue));
		//		service.execute(new Push(queue)); // 该线程保持插入数据
		service.shutdown();

		logger.debug("start");

	}
}

class Push implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Push.class);

	private final DelayQueue<DelayTask> q;

	public Push(DelayQueue<DelayTask> q) {
		super();
		this.q = q;
	}

	@Override
	public void run() {
		int i = 30;
		while (true) {
			long delay = (long) (Math.random() * 20000);
			q.put(new DelayTask(delay, i++));

			logger.debug("index:" + (i - 1) + "被放入, delay:" + delay);

			try {
				Thread.sleep(5000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class Consumer implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	DelayQueue<DelayTask> q;

	public Consumer(DelayQueue<DelayTask> _q) {
		q = _q;
	}

	@Override
	public void run() {
		while (true) {
			try {
				logger.debug("" + q.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class DelayTask implements Delayed {
	private static int count = 0;
	private final int id = count++;
	private final long nanoTime = System.nanoTime();
	private final long delayInMilliseconds;
	private final long triger;
	private final int order;

	public DelayTask(long _delay, int _order) {
		order = _order;
		this.delayInMilliseconds = _delay;
		triger = nanoTime + TimeUnit.NANOSECONDS.convert(_delay, TimeUnit.MILLISECONDS);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(triger - System.nanoTime(), TimeUnit.NANOSECONDS);
		//		return triger - System.nanoTime();
	}

	@Override
	public int compareTo(Delayed o) {
		DelayTask delayTask = (DelayTask) o;
		return triger > delayTask.triger ? 1 : triger < delayTask.triger ? -1 : 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + order;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DelayTask other = (DelayTask) obj;
		if (order != other.order)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task#" + id + " in index " + order + ": delaying " + delayInMilliseconds + " miliseconds picked up.";
	}
}
