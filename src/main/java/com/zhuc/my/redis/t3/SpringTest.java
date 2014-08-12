package com.zhuc.my.redis.t3;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhuc.my.redis.t2.Person;

/**
 * spring-data-redis实例
 * @author zhuc
 * @create 2013-6-18 下午3:31:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/redis/applicationContext-redis.xml" })
public class SpringTest {

	@Autowired
	private StringRedisTemplate stringTemplate;

	@Test
	public void saveString() {
		final Person p = new Person(1, "hello");
		stringTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(stringTemplate.getStringSerializer().serialize("person.uid." + p.getId()),
						stringTemplate.getStringSerializer().serialize(p.getName()));
				return null;
			}
		});
	}

	@Test
	public void readString() {
		final int uid = 1;
		Person p = stringTemplate.execute(new RedisCallback<Person>() {
			@Override
			public Person doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = stringTemplate.getStringSerializer().serialize("person.uid." + uid);
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					String name = stringTemplate.getStringSerializer().deserialize(value);
					Person p = new Person();
					p.setName(name);
					p.setId(uid);
					return p;
				}
				return null;
			}
		});
		System.out.println(p);
	}

	@Test
	public void deleteString() {
		final int uid = 1;
		stringTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) {
				connection.del(stringTemplate.getStringSerializer().serialize("person.uid." + uid));
				return null;
			}
		});
	}

	/**
	 * 存储对象(HSET)
	 */
	@Test
	public void saveObject() {
		final Person p = new Person(10, "shiro");
		stringTemplate.execute(new RedisCallback<Person>() {
			@Override
			public Person doInRedis(RedisConnection connection) throws DataAccessException {
				String key = "person.uid." + p.getId();
				BoundHashOperations<String, String, String> boundHashOperations = stringTemplate.boundHashOps(key);
				Map<String, String> data = new HashMap<String, String>();
				data.put("name", p.getName());
				boundHashOperations.putAll(data);
				return null;
			}
		});
	}

	/**
	 * 获取对象(HGET)
	 */
	@Test
	public void readObject() {
		final int uid = 10;
		Person p = stringTemplate.execute(new RedisCallback<Person>() {
			@Override
			public Person doInRedis(RedisConnection connection) throws DataAccessException {
				String key = "person.uid." + uid;
				BoundHashOperations<String, String, String> boundHashOperations = stringTemplate.boundHashOps(key);
				Map<String, String> data = boundHashOperations.entries();
				if (data != null) {
					Person p = new Person();
					p.setId(uid);
					p.setName(data.get("name"));
					return p;
				} else {
					return null;
				}
			}
		});
		System.out.println(p);
	}
}
