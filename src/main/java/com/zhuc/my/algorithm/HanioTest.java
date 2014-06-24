package com.zhuc.my.algorithm;

/**
 * 汉诺塔问题
 * @author zhuc
 * @create 2013-11-1 上午9:13:04
 */
public class HanioTest {

	private static int count = 1;

	private static void move(int n, String a, String b, String c) {
		if (n == 1) {
			System.out.println("count " + count++ + ":" + a + "->" + c);
		} else {
			move(n - 1, a, c, b); //n-1个从a通过b移动到c
			System.out.println("count " + count++ + ":" + a + "->" + c);
			move(n - 1, b, a, c);
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		move(5, "a", "b", "c");

		System.out.println("共需要" + (count - 1) + "步完成");
	}

}
