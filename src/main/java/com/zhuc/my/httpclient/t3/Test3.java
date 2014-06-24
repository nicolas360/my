package com.zhuc.my.httpclient.t3;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.http.client.ClientProtocolException;

import com.google.common.collect.ImmutableMap;
import com.zhuc.my.httpclient.utils.HttpProxy;

/**
 * 工具类测试
 * @author zhuc
 * @create 2013-6-19 下午1:31:20
 */
public class Test3 {

	/**
	 * @param args
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		HttpProxy proxy = new HttpProxy();
		proxy.accessGet("http://localhost:8080/my/servlet/HttpClientServlet1?s=test-get");

		proxy.accessPost("http://localhost:8080/my/servlet/HttpClientServlet1", new My("test-post"));

		ImmutableMap<String, String> map = ImmutableMap.of("s", "test-post-map", "t", "dd");
		proxy.accessPost("http://localhost:8080/my/servlet/HttpClientServlet1", map);

		proxy.close();
	}

}
