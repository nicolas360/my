package com.zhuc.my.jdk.serialize.t1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.alibaba.fastjson.JSON;

public class Test1 {

	private static final String filePath = "e:\\user.txt";

	/**
	 *将对象序列化到磁盘文件中
	 *@param o
	 *@throws Exception
	 */

	public static void writeObject(Object o) throws Exception {
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}

		FileOutputStream os = new FileOutputStream(f);
		//ObjectOutputStream 核心类
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(o);
		oos.close();
		os.close();
	}

	/**
	 *反序列化,将磁盘文件转化为对象
	 *@param f
	 *@return
	 *@throws Exception
	 */
	public static User readObject(String path) throws Exception {
		InputStream is = new FileInputStream(new File(path));
		//ObjectOutputStream 核心类
		ObjectInputStream ois = new ObjectInputStream(is);
		return (User) ois.readObject();
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		User u = new User(1l, "zhuc", 26);
		System.out.println(u);
		writeObject(u);
		User u2 = readObject(filePath);
		System.out.println(u2);

		System.out.println(JSON.toJSONString(u));
	}

}
