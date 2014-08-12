package com.zhuc.my.jdk.serialize.t2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2013-6-18 上午9:33:03
 */
public class SerializeUtil {

	private static final Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

	/**
	 * 序列化到字节
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		if (null == object) {
			return null;
		}

		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;

		byte[] bytes = null;
		try {
			//序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			bytes = baos.toByteArray();
		} catch (Exception e) {
			logger.error("", e);
		}
		return bytes;
	}

	/**
	 * 字节反序列化
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		ByteArrayInputStream bais = null;

		Object obj = null;
		try {
			//反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (Exception e) {
			logger.error("", e);
		}
		return obj;
	}

}
