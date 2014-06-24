package com.zhuc.my.jdk.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test1 {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String s = "1";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100000; i++) {
			s += i;
			//			sb.append(i);
		}
		System.out.println(s);
		//		System.out.println(sb.toString());
	}

}

class My implements Callable<Integer> {

	private static final Logger logger = LoggerFactory.getLogger(My.class);

	@Override
	public Integer call() throws Exception {
		Thread.sleep(3000l);
		Integer n = new Random().nextInt(100);
		logger.debug("random: " + n);
		return n;
	}

}