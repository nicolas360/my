package com.zhuc.my.jdk.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 如果在主线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，可以把这些作业交给Future对象在后台完成，当主线程将来需要时，就可以通过Future对象获得后台作业的计算结果或者执行状态
 * @version		2014-8-27 下午2:17:21
 * @author		zhuc
 */
public class Test2 {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> c = new MyCallable();
		FutureTask<Integer> ft = new FutureTask<Integer>(c);// 将Callable写的任务封装到一个由执行者调度的FutureTask对象

		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(ft);
		service.shutdown();

		while (!ft.isDone()) {
			try {
				Thread.sleep(1000l);
				System.out.println("未完成继续等待...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println(ft.get());
	}

}
