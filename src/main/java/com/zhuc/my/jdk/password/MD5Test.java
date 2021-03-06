package com.zhuc.my.jdk.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

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
		System.out.println(hex(after));

		// 结果一致
		String salt = "1qaz";
		System.out.println("after1: " + md5(s, salt));
		System.out.println("after2: " + DigestUtils.md5Hex(Bytes.concat(s.getBytes(), salt.getBytes())));
		System.out.println("after3: " + DigestUtils.md5Hex(s + salt));
		System.out.println("after shiro: " + new Md5Hash(salt, s).toHex()); //此方法等价于上述jdk加盐方法
		System.out.println("after shiro: " + new Md5Hash(s, salt).toHex()); //shiro提供的方法加盐与默认的位置相反

		System.out.println("after: " + DigestUtils.md5Hex(s));
		System.out.println("after shiro: " + new Md5Hash(s, null).toHex());
	}

	private static String md5(String str, String salt) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(str.getBytes());
		digest.update(salt.getBytes()); // 加盐
		byte[] after = digest.digest();

		return hex(after);
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
