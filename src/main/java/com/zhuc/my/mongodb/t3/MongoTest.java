package com.zhuc.my.mongodb.t3;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.QueryOperators;

public class MongoTest {

	private static List<DBObject> documents = new ArrayList<DBObject>();
	private static DBCollection coll;

	//	@Test
	public void init() {

	}

	/**
	 * and操作
	 */
	//	@Test
	public void testAnd() {
		//agender='female' AND age > 27	
		DBObject queryCondition = new BasicDBObject();
		queryCondition.put("agender", "female");
		queryCondition.put("age", new BasicDBObject("$gt", 27));
		DBCursor dbCursor = coll.find(queryCondition);
		Assert.assertEquals(1, dbCursor.size());
		Assert.assertEquals("Jane", dbCursor.next().get("username"));
	}

	/**
	 * 单个or
	 */
	//	@Test
	public void testOrSingleField() {
		DBObject queryCondition = new BasicDBObject();
		//age<15 OR age>27
		queryCondition = new BasicDBObject();
		BasicDBList values = new BasicDBList();
		values.add(new BasicDBObject("age", new BasicDBObject("$gt", 27)));
		values.add(new BasicDBObject("age", new BasicDBObject("$lt", 15)));
		queryCondition.put("$or", values);

		DBCursor dbCursor = coll.find(queryCondition);
		Assert.assertEquals(3, dbCursor.size());
		Assert.assertEquals("Tom", dbCursor.next().get("username"));
	}

	/**
	 * 多个字段之间的OR
	 */
	//	@Test
	public void testOrMultiFields() {
		DBObject queryCondition = new BasicDBObject();
		//agender=female OR age<=23
		queryCondition = new BasicDBObject();
		BasicDBList values = new BasicDBList();
		values.add(new BasicDBObject("agender", "female"));
		values.add(new BasicDBObject("age", new BasicDBObject("$lte", 23)));
		queryCondition.put("$or", values);

		DBCursor dbCursor = coll.find(queryCondition);
		Assert.assertEquals(5, dbCursor.size());
		Assert.assertEquals("Jim", dbCursor.next().get("username"));
	}

	/**
	 * 单个字段的IN操作
	 */
	@Test
	public void testIn() {
		DBObject queryCondition = new BasicDBObject();
		//age in [13, 47]
		queryCondition = new BasicDBObject();
		BasicDBList values = new BasicDBList();
		values.add(13);
		values.add(47);
		queryCondition.put("age", new BasicDBObject("$in", values));
		queryCondition.put("age", new BasicDBObject(QueryOperators.IN, values));

		DBCursor dbCursor = coll.find(queryCondition);
		Assert.assertEquals(2, dbCursor.size());
		Assert.assertEquals("Tom", dbCursor.next().get("username"));
	}

	private static void initConnection() throws UnknownHostException {
		//Create a connection to Collection 'user'
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("test");
		coll = db.getCollection("user");
	}

	private static void loadData() {
		DBObject db1 = new BasicDBObject();
		db1.put("uid", 10);
		db1.put("username", "Jim");
		db1.put("age", 23);
		db1.put("agender", "male");
		DBObject db2 = new BasicDBObject();
		db2.put("uid", 27);
		db2.put("username", "Tom");
		db2.put("age", 13);
		db2.put("agender", "male");
		DBObject db3 = new BasicDBObject();
		db3.put("uid", 12);
		db3.put("username", "Jane");
		db3.put("age", 31);
		db3.put("agender", "female");
		DBObject db4 = new BasicDBObject();
		db4.put("uid", 23);
		db4.put("username", "Alex");
		db4.put("age", 47);
		db4.put("agender", "male");
		DBObject db5 = new BasicDBObject();
		db5.put("uid", 109);
		db5.put("username", "Lily");
		db5.put("age", 24);
		db5.put("agender", "female");

		documents.add(db1);
		documents.add(db2);
		documents.add(db3);
		documents.add(db4);
		documents.add(db5);
	}

	@Before
	public void setUp() throws UnknownHostException {
		//Insert all data into MongoDB
		initConnection();
		//		loadData();
		//
		//		coll.insert(documents);
	}

	@After
	public void cleanUp() {
		//Drop the collection to remove all data.
		//Note: it's not recommended.
		// coll.drop();
	}
}
