package com.zhuc.my.guava.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class Test {

	private static final Logger logger = LoggerFactory.getLogger(Test.class);

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		Cache<String, Object> cache = CacheBuilder.newBuilder().maximumSize(10000).weakKeys().softValues()/**.refreshAfterWrite(120, TimeUnit.SECONDS)*/
		.expireAfterWrite(3, TimeUnit.SECONDS).removalListener(new RemovalListener<String, Object>() {
			@Override
			public void onRemoval(RemovalNotification<String, Object> rn) {
				logger.debug(rn.getKey() + "被移除");

			}
		}).build();

		final String s = "1";
		Object o = cache.get("aaa", new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				logger.debug("睡觉");
				//				Thread.sleep(10000l);
				return s;
			}
		});
		logger.debug("睡觉2");
		logger.debug("" + cache.getIfPresent("aaa"));
		logger.debug("" + o);
		Thread.sleep(5000l);
		logger.debug("" + cache.getIfPresent("aaa"));

		//		cache.put("bbb", "ccc");
		//
		//		System.out.println(cache.asMap());
		//		ConcurrentMap cmap = cache.asMap();
		//		cmap.remove("bbb");
		//		System.out.println(cache.asMap());
		//		logger.debug("" + cache.getIfPresent("bbb"));
		//		Thread.sleep(5000l);
		//		logger.debug("" + cache.getIfPresent("bbb"));
		//		logger.debug("" + cache.getIfPresent("bbb"));

	}

}
