package com.zhuc.my.jdk.number;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double d1 = 0.05;
		double d2 = 0.01;

		// 全部精度丢失
		System.out.println(d1 + d2);
		System.out.println(0.05 + 0.01);
		System.out.println(1 - 0.42);
		System.out.println(4.015 * 100);
		System.out.println(123.3 / 100);

		// 精度准确
		System.out.println(DoubleUtils.add(d1, d2));
		System.out.println(DoubleUtils.add(0.05, 0.01));
		System.out.println(DoubleUtils.sub(1, 0.42));
		System.out.println(DoubleUtils.mul(4.015, 100));
		System.out.println(DoubleUtils.div(123.3, 100));
		//		System.out.println(DoubleUtils.round(-123.9, 10));

		System.out.println(DoubleUtils.div(121460785l, Math.pow(10, 6)));
		System.out.println(DoubleUtils.div(31276010l, Math.pow(10, 6)));
		System.out.println(121460785 / Math.pow(10, 6));

	}
}
