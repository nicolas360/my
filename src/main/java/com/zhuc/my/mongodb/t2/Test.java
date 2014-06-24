package com.zhuc.my.mongodb.t2;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryOperators;
import com.mongodb.ServerAddress;

public class Test {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient mc1 = new MongoClient();
		MongoClient mc2 = new MongoClient("localhost");
		MongoClient mc3 = new MongoClient("localhost", 27017);
		MongoClient mc4 = new MongoClient(ImmutableList.<ServerAddress> of(new ServerAddress("localhost", 27017)));

		System.out.println(mc1);
		DB db = mc1.getDB("test");
		System.out.println(db.getCollectionNames());

		DBCollection dc = db.getCollection("users");
		System.out.println(dc.count());
		//		System.out.println(dc.findOne());

		BasicDBObject doc = new BasicDBObject("name", "zhuc").append("age", 22).append("sex", "男")
				.append("obj", new BasicDBObject("x", "123.456")).append("_id", 123);
		//		dc.insert(doc);

		DBCursor cur = dc.find();
		while (cur.hasNext()) {
			System.out.println(cur.next().toString());
		}

		System.out.println("==============");

		BasicDBObject query = new BasicDBObject(ImmutableMap.of("name", "zhu"));
		cur = dc.find(query);
		while (cur.hasNext()) {
			//			System.out.println(cur.next().toString());
			DBObject dbo = cur.next();
			System.out.println(dbo);
		}

		System.out.println("==============");

		BasicDBObject query2 = new BasicDBObject("age", new BasicDBObject(QueryOperators.GTE, 21));
		cur = dc.find(query2, new BasicDBObject("_id", 0).append("_class", 0)); //不显示id和class
		while (cur.hasNext()) {
			//			System.out.println(cur.next().toString());
			DBObject dbo = cur.next();
			System.out.println(dbo);
		}

		//		dc.createIndex(new BasicDBObject("age", 1)); //1/升序、-1/降序

		System.out.println(dc.getIndexInfo());

		System.out.println(mc1.getDatabaseNames());

		//		mc1.dropDatabase("test");

		System.out.println("========================================");

		Pattern p = Pattern.compile("zhu?");
		cur = dc.find(new BasicDBObject("name", p)); //不显示id和class
		while (cur.hasNext()) {
			//			System.out.println(cur.next().toString());
			DBObject dbo = cur.next();
			System.out.println(dbo);
		}

		dc.insert(new BasicDBObject("hello", new Boy("boy-1")).append("age", 30));

		//		dc.remove(new BasicDBObject("name", "zhu"));
		dc.update(new BasicDBObject("_id", 123), new BasicDBObject("age", 122).append("name", "zhuc")
				.append("sex", "男"));

		//		dc.insert(new BasicDBObject("name", "test3").append("hi", new Date()));
		DBCursor cur2 = dc.find(new BasicDBObject("name", "test").append("hi", new BasicDBObject("$lte", new Date())));
		while (cur2.hasNext()) {
			DBObject db2 = cur2.next();
			System.out.println(db2.get("hi"));
			System.out.println(db2.get("hi") instanceof Date);
		}
	}

}

class Boy {
	/**
	 * 
	 */
	private String bname;

	/**
	 * @return the bname
	 */
	public String getBname() {
		return bname;
	}

	/**
	 * @param bname the bname to set
	 */
	public void setBname(String bname) {
		this.bname = bname;
	}

	public Boy(String bname) {
		super();
		this.bname = bname;
	}

	public Boy() {
		super();
	}

}