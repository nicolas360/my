package com.zhuc.my.guava;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;

/**
 * @author zhuc
 * @create 2013-4-7 下午1:46:25
 */
public class Test2 {

	/**
	 * 
	 */
	@Test
	public void show() {
		List<User> personList = Lists.newLinkedList();
		Set<User> personSet = Sets.newHashSet();
		Map<String, User> personMap = Maps.newHashMap();
		Integer[] intArrays = ObjectArrays.newArray(Integer.class, 10);

		// --------------------

		Set<String> set = Sets.newHashSet("one", "two", "three");
		List<String> list = Lists.newArrayList("one", "two", "three");
		Map<String, String> map = ImmutableMap.of("ON", "TRUE", "OFF", "FALSE");

		//2,简化集合的初始化
		List<User> personList2 = Lists.newArrayList(new User("zhuc-1", 20), new User("zhuc-2", 25));
		Set<User> personSet2 = Sets.newHashSet(new User("zhuc-1", 20), new User("zhuc-2", 25));
		Map<String, User> personMap2 = ImmutableMap.of("hello", new User("zhuc-1", 20), "fuck", new User("zhuc-2", 25));
	}
}
