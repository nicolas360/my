package com.zhuc.my.guava;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

/**
 * @author zhuc
 * @create 2013-6-8 下午1:46:56
 */
public class Test1 {

	@Test
	public void t1() {
		Map<String, Map<Long, List<String>>> map = Maps.newHashMap();

		ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");
		ImmutableMap<String, String> map2 = ImmutableMap.of("key1", "value1", "key2", "value2");

		File file = new File(getClass().getResource("/log4j.properties").getFile());
		List<String> lines = null;
		try {
			lines = Files.readLines(file, Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ...
	}
}
