package com.zhuc.my.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test3 {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "我们";
		System.out.println(System.getProperty("file.encoding"));
		System.out.println(new String(s.getBytes()) + ":" + s.getBytes().length);
		System.out.println(new String(s.getBytes("iso-8859-1")) + ":" + s.getBytes("iso-8859-1").length);
		System.out.println(new String(s.getBytes("gbk")) + ":" + s.getBytes("gbk").length);

		System.out.println(new String(s.getBytes("gbk"), "gbk"));

		System.out.println("==========");

		System.out.println("\u53d6");
		System.out.println(new String(("\u53d6").getBytes(), "utf-8"));

		// java 中字符是unicode进行存储的
		char c = '我';
		System.out.println((int) c); //25105
		System.out.println(Integer.toHexString(c)); //0x6211
		System.out.println("\u6211");

		String ss1 = "我";
		System.out.println(hex(ss1.getBytes("iso-8859-1")));
		System.out.println(hex(ss1.getBytes("utf-8")));
		System.out.println(hex(ss1.getBytes("gbk")));

		byte[] b = new byte[] { (byte) 0xcc, (byte) 0xe1, (byte) 0xbd, (byte) 0xbb };
		String ss2 = new String(b, "iso-8859-1");
		System.out.println(ss2);
		System.out.println(new String(ss2.getBytes("iso-8859-1"), "gbk"));

		System.out.println(hex(ss2.getBytes("iso-8859-1")));
		System.out.println(hex(ss2.getBytes("gbk")));
		System.out.println(hex(ss2.getBytes("utf-8")));

		String e1 = URLEncoder.encode("你", "utf-8");
		String d1 = URLDecoder.decode(e1, "utf-8");
		System.out.println(d1);

		String a1 = new String("阿什顿".getBytes("utf-8"), "iso-8859-1");
		// 阿什顿 --> 123 456 789 --> 1 2 3 4 5 6 7 8 9 --> 9个特殊字符
		String b1 = new String(a1.getBytes("iso-8859-1"), "utf-8");
		// 9个特殊字符 --> 1 2 3 4 5 6 7 8 9 --> 123 456 789 --> 阿什顿
		System.out.println("阿什顿".getBytes().length);
		System.out.println("a1: " + a1);
		System.out.println("b1: " + b1);

		// 没有字节丢失的情况下, 转码是可以成功的
		String a2 = new String("阿什顿".getBytes("gbk"), "iso-8859-1");
		// 阿什顿 --> 12 34 56 --> 1 2 3 4 5 6 --> 6个特殊字符
		String b2 = new String(a2.getBytes("iso-8859-1"), "gbk");
		// 6个特殊字符 --> 1 2 3 4 5 6--> 12 34 56 --> 阿什顿
		System.out.println("阿什顿".getBytes().length);
		System.out.println("a2: " + a2);
		System.out.println("b2: " + b2);

		String s1 = new String("长安".getBytes("utf-8"), "gbk");
		//		长安 --> 123 456 --> 12 34 56 --> 蔢 哦 就 (假设, 也许不存在)
		//		蔢 哦 就 (假设, 也许不存在) --> 12 34 56 --> 123 456 --> 长安
		System.out.println(new String(s1.getBytes("gbk"), "utf-8"));
		System.out.println("-------------------");

		String s2 = new String("长安街".getBytes("utf-8"), "gbk");
		//		长安街 --> 123 456 789 --> 12 34 56 78 9--> 蔢 哦 就 撒 ?(假设, 也许不存在, 最后一个字的编码后已丢失字节)
		//		蔢 哦 就 撒 ?(假设, 也许不存在) --> 12 34 56 78 x (最后一个字的编码后已丢失字节, 已不能再获取编码后的字节数组) 
		//		--> 123 456 78x --> 长安?
		System.out.println(new String(s2.getBytes("gbk"), "utf-8"));
		System.out.println("-------------------");

		System.out.println(hex("长安街".getBytes("utf-8")));
		System.out.println(hex(s2.getBytes("gbk")));

		char[] cc = new char[] { '频', '率' };
		for (char d : cc) {
			System.out.println(Integer.toHexString(d)); // 9891 7387
		}
		System.out.println(new String("\u9891\u7387"));

		byte[] bb = "频率".getBytes("gbk");
		for (byte d : bb) {
			System.out.println(Integer.toHexString(d & 0xff));
		}

	}

	private static String hex(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (byte c : b) {
			sb.append("0x" + (Integer.toHexString(c & 0xff)) + " ");
		}

		return sb.toString();
	}

}
