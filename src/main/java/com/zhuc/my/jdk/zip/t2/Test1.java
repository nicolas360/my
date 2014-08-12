package com.zhuc.my.jdk.zip.t2;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ZipCompressor zc = new ZipCompressor("E:\\szhzip2.zip");
		//		zc.compress("E:\\学习\\地图\\高德\\t2.jsp");
		zc.compress("E:\\学习\\地图\\高德");

		//		ZipCompressorByAnt zca = new ZipCompressorByAnt("E:\\学习\\地图\\高德\\szhzip.zip");
		//		zca.compress("E:\\学习\\地图\\高德");
	}

}
