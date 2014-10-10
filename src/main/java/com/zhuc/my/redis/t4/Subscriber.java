package com.zhuc.my.redis.t4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPubSub;

/**
 * 订阅
 * @version		2014-10-9 上午10:03:08
 * @author		zhuc
 */
public class Subscriber extends JedisPubSub {

	private static Logger logger = LoggerFactory.getLogger(Subscriber.class);

	@Override
	public void onMessage(String channel, String message) {
		logger.info("onMessage received. Channel: {}, Msg: {}", channel, message);

	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
		logger.info("onPMessage received. pattern: {}, Channel: {}, Msg: {}", pattern, channel, message);
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		logger.info("onSubscribe. Channel: {}, subscribedChannels: {}", channel, subscribedChannels);

	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {

	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {

	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {

	}

}
