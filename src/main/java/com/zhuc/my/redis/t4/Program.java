package com.zhuc.my.redis.t4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 订阅发布
 * @version		2014-10-10 下午1:47:02
 * @author		zhuc
 */
public class Program {

	public static final String CHANNEL_NAME = "commonChannel";

	private static Logger logger = LoggerFactory.getLogger(Program.class);

	private static ExecutorService service = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws Exception {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		JedisPool jedisPool = new JedisPool(poolConfig, "192.168.199.140", 6379, 0, "redis");

		final Jedis subscriberJedis = jedisPool.getResource();
		final Subscriber subscriber = new Subscriber();

		service.execute(new Runnable() {
			@Override
			public void run() {
				try {
					logger.info("Subscribing to \"commonChannel\". This thread will be blocked.");
					subscriberJedis.subscribe(subscriber, CHANNEL_NAME);

					//可以订阅多个频道
					//订阅得到信息在lister的onMessage(...)方法中进行处理
					//				subscriberJedis.subscribe(subscriber, "hello_foo", "hello_test");

					//				//也用数组的方式设置多个频道
					//				subscriberJedis.subscribe(subscriber, new String[] { "hello_foo", "hello_test" });

					//				//这里启动了订阅监听，线程将在这里被阻塞
					//				//订阅得到信息在lister的onPMessage(...)方法中进行处理
					//					subscriberJedis.psubscribe(subscriber, new String[] { "hello_*" });//使用模式匹配的方式设置频道

					logger.info("Subscription ended.");
				} catch (Exception e) {
					logger.error("Subscribing failed.", e);
				}
			}
		});

		Jedis publisherJedis = jedisPool.getResource();
		new Publisher(publisherJedis, CHANNEL_NAME).start();

		subscriber.unsubscribe();
		jedisPool.returnResource(subscriberJedis);
		jedisPool.returnResource(publisherJedis);
	}

}
