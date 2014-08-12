package com.zhuc.my.httpclient.httpclient4;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.google.common.collect.Maps;
import com.zhuc.my.httpclient.utils.HttpProxy4;

public class Test2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpProxy4 proxy = new HttpProxy4();
		proxy.accessGet("http://192.168.70.12:8080/bicycle");

		System.out.println("=================");

		proxy = new HttpProxy4();
		Map<String, String> map = Maps.newHashMap();
		map.put("userName", "vip");
		map.put("password", "secret");
		proxy.accessPost("http://192.168.70.12:8080/bicycle/login", map);
	}

}
