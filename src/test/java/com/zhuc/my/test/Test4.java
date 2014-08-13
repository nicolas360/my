package com.zhuc.my.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;

public class Test4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateTime dt = new DateTime();
		dt = dt.hourOfDay().addToCopy(2).minuteOfHour().addToCopy(49);
		System.out.println(dt);

		System.out.println((2 * 60 + 49) / 5.0);

		System.out.println(Arrays.asList(new String[] { "1", "3" }).getClass());
		System.out.println(new ArrayList<String>().getClass());

		List<String> list = Arrays.asList(new String[] { "1", "3" });
		//		list.add("4"); // error

		List<String> list2 = new ArrayList<String>(Arrays.asList(new String[] { "1", "3" }));
		list2.add("4"); // right

	}
}
