package com.zhuc.my.memcached.xmemcached;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.KeyIterator;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class Test1 {

	//	private static final String address = "192.168.8.134:12000";
	private static final String address = "localhost:11211";

	/**
	 * @param args
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(address));
		MemcachedClient memcachedClient = builder.build();

		try {
			//			memcachedClient.set("hello", 0, "Hello,xmemcached");
			//			String value = memcachedClient.get("hello");
			//			System.out.println("hello=" + value);
			//			memcachedClient.delete("hello");
			//			value = memcachedClient.get("hello");
			//			System.out.println("hello=" + value);

			KeyIterator o = memcachedClient.getKeyIterator(AddrUtil.getOneAddress(address));
			System.out.println("-------------------start---------------------");
			while (o.hasNext()) {
				System.out.println(o.next());
			}
			System.out.println("-------------------end---------------------");

			System.out.println(memcachedClient.get("com.zhuc.my.spring.memcached.dao.StudentDao:200"));

		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// ignore 
		}

		try {
			memcachedClient.shutdown();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		}
	}

}
