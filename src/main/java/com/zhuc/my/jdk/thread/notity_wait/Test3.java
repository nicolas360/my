package com.zhuc.my.jdk.thread.notity_wait;

import java.util.HashMap;

public class Test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new User(i)).start();
		}
	}

}

/**
 * 这里注意runnable接口的线程是怎么实例化的。new Thread(new User())
 * 这里成功展示了多个用户存取同一个账户的多线程实例，通过多线程同步，保证了安全的执行。
 * @author abc
 * 
 */
class User implements Runnable {
	private static Account account = new Account();
	private final int id;

	User(int i) {
		id = i;
	}

	public void run() {
		int tempMoney = 100;
		account.load("ren", tempMoney);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		account.save("ren", 100);
		System.out.println("线程" + id + "完毕========================================================");
	}
}

class Account {
	private static HashMap<String, Integer> m = new HashMap<String, Integer>();
	private static long times = 0;
	static {
		m.put("ren", 1000);
	}

	public synchronized void save(String name, int num) {
		long tempTime = times++;
		System.out.println("第 " + tempTime + " 次存储" + num + "之前" + name + "的余额为：" + m.get(name));
		m.put(name, m.get(name) + num);
		this.notify();
		System.out.println("第 " + tempTime + " 次存储" + num + "之后" + name + "的余额为：" + m.get(name));
	}

	public static int get(String name) {
		return m.get(name);
	}

	/**
	 * 注意wait的用法，必须在loop中，必须在拥有锁的代码块中。 前者是当被notify的时候要重新进行条件判断，后者是为了释放锁。
	 * 
	 * @param name
	 * @param num
	 */
	public synchronized void load(String name, int num) {
		long tempTime = times++;
		System.out.println("第 " + tempTime + " 次提取" + num + "之前" + name + "的余额为：" + m.get(name));

		try {
			while (m.get(name) < num) {
				System.out.println("第 " + tempTime + " 次提取" + "余额" + m.get(name) + "不足，开始等待wait。");
				this.wait();
				System.out.println("第 " + tempTime + " 次提取操作被唤醒");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.put(name, m.get(name) - num);
		System.out.println("第 " + tempTime + " 次提取" + num + "之后" + name + "的余额为：" + m.get(name));
	}
}