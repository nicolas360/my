package com.zhuc.my.jdk.number;

import java.math.BigDecimal;

public class DoubleUtils {

	/**
	 * 加法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double add(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 减法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double sub(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 乘法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double mul(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 除法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double div(double d1, double d2) {
		return div(d1, d2, 10);
	}

	/**
	 * 除法
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double div(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("need > 0");
		}

		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 四舍五入
	 * @param d
	 * @param scale
	 * @return
	 */
	public static double round(double d, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("need > 0");
		}

		BigDecimal b = new BigDecimal(Double.toString(d));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
