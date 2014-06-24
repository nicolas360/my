package com.zhuc.my.memcached.spring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.UpdateSingleCache;

/**
 * @author zhuc
 * @create 2013-6-21 下午1:47:13
 */
@Component("studentDao")
public class StudentDao {

	private static final String ns = "com.zhuc.my.spring.memcached.dao.StudentDao";

	private static final Map<String, Student> studentMap = new ConcurrentHashMap<String, Student>();

	/**
	 * @param s
	 */
	//	@CacheName("cache1")
	public void save(Student s) {
		studentMap.put(s.getId(), s);
	}

	/**
	 * 当执行get查询方法时，系统首先会从缓存中获取id对应的实体 
	 * 如果实体还没有被缓存，则执行查询方法并将查询结果放入缓存中 
	 * @param id
	 * @return
	 */
	//	@CacheName("cache1")
	@ReadThroughSingleCache(namespace = ns, expiration = 3600)
	public Student get(@ParameterValueKeyProvider String id) {
		Student s = new Student(id, "lol22222", 1222211111);
		return s;
	}

	/**
	 * 当执行update方法时，系统会更新缓存中id对应的实体 
	 * 将实体内容更新成@ParameterDataUpdateContent标签所描述的实体 
	 * @param s 
	 */
	//	@CacheName("cache1")
	@UpdateSingleCache(namespace = ns, expiration = 3600)
	public void update(@ParameterValueKeyProvider @ParameterDataUpdateContent Student s) {
	}

	/** 
	 * 当执行delete方法时，系统会删除缓存中id对应的实体 
	 * @param id 
	 */
	//	@CacheName("cache1")
	@InvalidateSingleCache(namespace = ns)
	public void delete(@ParameterValueKeyProvider String id) {
	}

}
