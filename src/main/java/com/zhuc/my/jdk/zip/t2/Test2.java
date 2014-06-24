package com.zhuc.my.jdk.zip.t2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class Test2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int BUFFER = 8092;
		FileOutputStream fileOutputStream = new FileOutputStream("E:\\学习\\地图\\高德\\file2.zip");
		CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
		ZipOutputStream out = new ZipOutputStream(cos);
		String basedir = "";

		File file = new File("E:\\学习\\地图\\高德\\t1.jsp");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		ZipEntry entry = new ZipEntry(basedir + file.getName());
		out.putNextEntry(entry);
		int count;
		byte data[] = new byte[BUFFER];
		while ((count = bis.read(data, 0, BUFFER)) != -1) {
			out.write(data, 0, count);
		}
		bis.close();
		out.close();
	}

}
