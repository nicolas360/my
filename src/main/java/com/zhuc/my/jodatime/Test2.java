package com.zhuc.my.jodatime;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

/**
 * @author zhuc
 * @create 2013-6-8 上午10:43:54
 */
public class Test2 {

	/**
	 * 
	 */
	@Test
	public void t1() {
		DateTime dt = new DateTime();// 取得当前时间

		// 根据指定格式,将时间字符串转换成DateTime对象
		DateTime dt2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2014-05-13 08:27:00");
		System.out.println(dt2.toDate());
		System.out.println(dt2.toDate().getTime());

		DateTime dt21 = DateTime.parse("2014/05/13 08:30:34", DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss"));
		System.out.println(dt21.toDate().getTime());

		// 年,月,日,时,分,秒,毫秒
		DateTime dt3 = new DateTime(2008, 2, 13, 10, 30, 50, 333);// 2008年2月13日10点30分50秒333毫秒
		System.out.println(dt3 + "是否闰年:" + dt3.year().isLeap());

		// 判断是否闰月
		DateTime dt4 = new DateTime();
		org.joda.time.DateTime.Property month = dt4.monthOfYear();
		System.out.println("是否闰月:" + month.isLeap());

		// 取得 3秒前的时间
		DateTime dt5 = dt.secondOfMinute().addToCopy(-3);
		dt.getSecondOfMinute();// 得到整分钟后，过的秒钟数
		dt.getSecondOfDay();// 得到整天后，过的秒钟数
		dt.secondOfMinute();// 得到分钟对象,例如做闰年判断等使用

		// DateTime与java.util.Date对象,当前系统TimeMillis转换
		DateTime dt6 = new DateTime(new Date());
		Date date = dt.toDate();
		DateTime dt7 = new DateTime(System.currentTimeMillis());
		dt.getMillis();

		// 将dt对象，按照指定格式输出字符串
		System.out.println(dt.toString("yyyy-MM-dd HH:mm:ss"));
	}

}
