package com.zhuc.my.jdk.nio.t1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 类FileGathering.java的实现描述：write two byte arrays to a same file.
 * 
 * @author yblin 2010-10-18 上午09:22:10
 */
public class FileGathering {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();

		File fileIn1 = new File("e:\\from1.txt");
		File fileIn2 = new File("e:\\from2.txt");
		File fileOut = new File("e:\\to.txt");

		FileInputStream fin1 = new FileInputStream(fileIn1);
		FileInputStream fin2 = new FileInputStream(fileIn2);
		FileOutputStream fout = new FileOutputStream(fileOut);

		FileChannel fcIn1 = fin1.getChannel();
		FileChannel fcIn2 = fin2.getChannel();
		ByteBuffer[] bufferArray = new ByteBuffer[2];
		bufferArray[0] = ByteBuffer.allocate(1024);
		bufferArray[1] = ByteBuffer.allocate(1024);
		fcIn1.read(bufferArray[0]);
		fcIn2.read(bufferArray[1]);
		bufferArray[0].flip();
		bufferArray[1].flip();
		FileChannel fcOut = fout.getChannel();
		fcOut.write(bufferArray);
		long end = System.currentTimeMillis();
		System.out.println("time used: 	" + (end - start) + "ms");

		fin1.close();
		fin2.close();
		fout.close();
	}
}
