package com.zhuc.my.ehcache.t1;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test1 {

	private static final Logger logger = LoggerFactory.getLogger(Test1.class);

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		ColorCache cache = new ColorCache();
		//		logger.info("1:" + cache.getColor("red"));
		//		logger.info("2:" + cache.getColor("red"));
		System.out.println(cache.getTTL());
		System.out.println(cache.getTTI());
		System.out.println(cache.getSize());

		CacheManager manager = CacheManager.create("src/main/java/com/zhuc/my/ehcache/t1/ehcache.xml");
		CacheManager manager2 = CacheManager.create();
		logger.info("manager == manager2: " + (manager == manager2));

		logger.info("cache: " + manager.getCache("colors").getKeys());
		logger.info("ehcache: " + manager2.getEhcache("colors").getKeys());

		Cache memoryCache = new Cache("memoryCache", 1000, false, false, 5, 2);
		manager.addCache(memoryCache);

		Cache mc = manager.getCache("memoryCache");
		System.out.println(mc.getCacheConfiguration().getTimeToLiveSeconds());

		mc.put(new Element("my", new Object()));
		System.out.println(mc.get("my"));
		Thread.sleep(2000l);
		System.out.println(mc.get("my"));
		Thread.sleep(5000l);
		System.out.println(mc.get("my"));

		manager.shutdown();
		manager2.shutdown();

	}

}
