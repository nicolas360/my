package com.zhuc.my.jdk.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class Test1 {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		//创建一个线程池
		ExecutorService pool = Executors.newCachedThreadPool();
		//创建有返回值的任务
		Callable<Integer> c = new MyCallable();
		//执行任务并获取Future对象
		Future<Integer> f = pool.submit(c);
		// 输出结果
		System.out.println(f.get()); // 阻塞等待
		//		System.out.println(f1.get(2l, TimeUnit.SECONDS)); // 阻塞等待指定时间, 超过时间后没返回数据将抛出异常
		//关闭线程池
		pool.shutdown();
	}

}

class MyCallable implements Callable<Integer> {

	@Override
	public Integer call() {
		int sum = 0;
		for (int i = 0; i <= 100; i++) {
			sum += i;
		}
		try {
			Thread.sleep(3000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Integer.valueOf(sum);
	}

}