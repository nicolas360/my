package com.zhuc.my.redis.t2;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.zhuc.my.jdk.serialize.t2.SerializeUtil;

public class Test2 {

	private Jedis redis;

	@Before
	public void init() {
		redis = new Jedis("192.168.253.137", 6379);
	}

	/**
	 * redis存储javabean
	 */
	@Test
	public void saveObject() {
		//		Person person = new Person(100, "alan");
		//		List<Person> list = Lists.newArrayList(person);
		//		redis.set("perList".getBytes(), SerializeUtil.serialize(list));

		byte[] bs = redis.get("perList".getBytes());
		List<Person> list2 = (List<Person>) SerializeUtil.unserialize(bs);
		System.out.println(list2);

		System.out.println(redis.keys("*"));
		System.out.println(redis.hget("person.uid.10", "name"));
	}
}
