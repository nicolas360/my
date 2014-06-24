package com.zhuc.my.jdk.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.primitives.Bytes;

public class MD5Test {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		byte[] b = new byte[8];
		SecureRandom random = new SecureRandom();
		random.nextBytes(b);

		System.out.println(Arrays.toString(b));

		String s = "111111";

		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(s.getBytes());
		digest.update(b); // 加盐
		byte[] after = digest.digest();

		// 结果一致
		System.out.println("after1: " + hex(after));
		System.out.println("after2: " + DigestUtils.md5Hex(Bytes.concat(s.getBytes(), b)));
		System.out.println("after: " + DigestUtils.md5Hex(s.getBytes()));

	}

	private static String hex(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (byte c : b) {
			String s = Integer.toHexString(c & 0xff);
			if (s.length() == 1) {
				s = "0" + s;
			}
			sb.append(s);
		}

		return sb.toString();
	}
}
