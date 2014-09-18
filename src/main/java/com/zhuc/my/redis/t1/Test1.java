package com.zhuc.my.redis.t1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class Test1 {

	private Jedis redis;

	@Before
	public void init() {
		redis = new Jedis("192.168.199.136", 6379);
	}

	/**
	 * key操作
	 */
	@Test
	public void key() {
		//KEYS    
		Set<String> keys = redis.keys("*");//列出所有的key，查找特定的key如：redis.keys("foo")    
		System.out.println(keys);

		System.out.println(redis.keys("name*"));

		//DEL 移除给定的一个或多个key。如果key不存在，则忽略该命令。    
		redis.del("name1");

		//TTL 返回给定key的剩余生存时间(time to live)(以秒为单位)    
		redis.ttl("foo");

		//PERSIST key 移除给定key的生存时间。    
		redis.persist("foo");

		//EXISTS 检查给定key是否存在。       
		redis.exists("foo");

		//MOVE key db  将当前数据库(默认为0)的key移动到给定的数据库db当中。如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定key，或者key不存在于当前数据库，那么MOVE没有任何效果。    
		redis.move("foo", 1);//将foo这个key，移动到数据库1    

		//RENAME key newkey  将key改名为newkey。当key和newkey相同或者key不存在时，返回一个错误。当newkey已经存在时，RENAME命令将覆盖旧值。    
		redis.rename("foo", "foonew");

		//TYPE key 返回key所储存的值的类型。    
		System.out.println(redis.type("foo"));//none(key不存在),string(字符串),list(列表),set(集合),zset(有序集),hash(哈希表)    

		//EXPIRE key seconds 为给定key设置生存时间。当key过期时，它会被自动删除。    
		redis.expire("foo", 5);//5秒过期    
		//EXPIREAT EXPIREAT的作用和EXPIRE一样，都用于为key设置生存时间。不同在于EXPIREAT命令接受的时间参数是UNIX时间戳(unix timestamp)。    

		//一般SORT用法 最简单的SORT使用方法是SORT key。    
		redis.lpush("sort", "1");
		redis.lpush("sort", "4");
		redis.lpush("sort", "6");
		redis.lpush("sort", "3");
		redis.lpush("sort", "0");

		List<String> list = redis.sort("sort");//默认是升序    
		System.out.println(list);
	}

	/**
	 * string操作
	 */
	@Test
	public void str() {
		//SET key value将字符串值value关联到key。    
		redis.set("name", "wangjun1");
		redis.set("id", "123456");
		redis.set("address", "guangzhou");

		//SETEX key seconds value将值value关联到key，并将key的生存时间设为seconds(以秒为单位)。    
		redis.setex("foo", 5, "haha");

		//MSET key value [key value ...]同时设置一个或多个key-value对。    
		redis.mset("haha", "111", "xixi", "222");

		//redis.flushAll();清空所有的key    
		System.out.println(redis.dbSize());//dbSize是多少个key的个数    

		//APPEND key value如果key已经存在并且是一个字符串，APPEND命令将value追加到key原来的值之后。    
		redis.append("foo", "00");//如果key已经存在并且是一个字符串，APPEND命令将value追加到key原来的值之后。    

		//GET key 返回key所关联的字符串值    
		redis.get("foo");

		//MGET key [key ...] 返回所有(一个或多个)给定key的值    
		List<String> list = redis.mget("haha", "xixi");
		System.out.println(list);

		//DECR key将key中储存的数字值减一。    
		redis.decr("haha");
		//DECRBY key decrement将key所储存的值减去减量decrement。    
		redis.decrBy("haha", 10);
		//INCR key 将key中储存的数字值增一。
		redis.incr("xixi");
		//INCRBY key increment 将key所储存的值加上增量increment。
		redis.incrBy("xixi", 20);
	}

	/**
	 * hash操作
	 */
	@Test
	public void hash() {
		//HSET key field value将哈希表key中的域field的值设为value。    
		redis.hset("website", "google", "www.google.cn");
		redis.hset("website", "baidu", "www.baidu.com");
		redis.hset("website", "sina", "www.sina.com");

		//HMSET key field value [field value ...] 同时将多个field - value(域-值)对设置到哈希表key中。    
		Map<String, String> map = new HashMap<String, String>();
		map.put("cardid", "123456");
		map.put("username", "jzkangta");
		redis.hmset("hash", map);

		//HGET key field返回哈希表key中给定域field的值。    
		System.out.println(redis.hget("hash", "username"));

		//HMGET key field [field ...]返回哈希表key中，一个或多个给定域的值。    
		List<String> list = redis.hmget("website", "google", "baidu", "sina");
		System.out.println(list);

		//HGETALL key返回哈希表key中，所有的域和值。    
		Map<String, String> map2 = redis.hgetAll("hash");
		System.out.println(map2);

		//HDEL key field [field ...]删除哈希表key中的一个或多个指定域。    
		redis.hdel("hash", "cardid");
		//HLEN key 返回哈希表key中域的数量。    
		redis.hlen("website");
		//HEXISTS key field查看哈希表key中，给定域field是否存在。
		redis.hexists("website", "baidu");
		//HINCRBY key field increment为哈希表key中的域field的值加上增量increment。
		redis.hincrBy("hash", "cardid", 10);
		//HKEYS key返回哈希表key中的所有域。    
		redis.hkeys("website");
		//HVALS key返回哈希表key中的所有值。
		redis.hvals("hash");
	}

	/**
	 * list操作
	 */
	@Test
	public void list() {
		//LPUSH key value [value ...]将值value插入到列表key的表头。    
		redis.lpush("list", "abc");
		redis.lpush("list", "xzc");
		redis.lpush("list", "erf");
		redis.lpush("list", "bnh");

		//LRANGE key start stop返回列表key中指定区间内的元素，区间以偏移量start和stop指定。下标(index)参数start和stop都以0为底，也就是说，以0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。你也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推。    
		List<String> list = redis.lrange("list", 0, -1);
		System.out.println(list);

		//LLEN key返回列表key的长度。
		redis.llen("list");
		//LREM key count value根据参数count的值，移除列表中与参数value相等的元素。
		redis.lrem("list", 0, "xzc");
		System.out.println(redis.lrange("list", 0, -1));
	}

	/**
	 * set操作
	 */
	@Test
	public void set() {
		//SADD key member [member ...]将member元素加入到集合key当中。    
		redis.sadd("testSet", "s1");
		redis.sadd("testSet", "s2");
		redis.sadd("testSet", "s3");
		redis.sadd("testSet", "s4");
		redis.sadd("testSet", "s5");

		//SREM key member移除集合中的member元素。    
		redis.srem("testSet", "s5");

		//SMEMBERS key返回集合key中的所有成员。    
		Set<String> set = redis.smembers("testSet");
		System.out.println(set);

		//SISMEMBER key member判断member元素是否是集合key的成员。是（true），否则（false）    
		System.out.println(redis.sismember("testSet", "s4"));

		//SCARD key返回集合key的基数(集合中元素的数量)。  
		redis.scard("testSet");
		//SMOVE source destination member将member元素从source集合移动到destination集合。    
		redis.smove("testSet", "testSet2", "s2");

		//SINTER key [key ...]返回一个集合的全部成员，该集合是所有给定集合的交集。    
		redis.sinter("testSet");
		//SINTERSTORE destination key [key ...]此命令等同于SINTER，但它将结果保存到destination集合，而不是简单地返回结果集    
		//SUNION key [key ...]返回一个集合的全部成员，该集合是所有给定集合的并集。    
		//SUNIONSTORE destination key [key ...]此命令等同于SUNION，但它将结果保存到destination集合，而不是简单地返回结果集。    
		//SDIFF key [key ...]返回一个集合的全部成员，该集合是所有给定集合的差集 。    
		//SDIFFSTORE destination key [key ...]此命令等同于SDIFF，但它将结果保存到destination集合，而不是简单地返回结果集。    
	}
}
