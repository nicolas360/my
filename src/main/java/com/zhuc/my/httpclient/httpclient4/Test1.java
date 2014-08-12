package com.zhuc.my.httpclient.httpclient4;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test1 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet("http://192.168.70.12:8080/bicycle");
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			// The underlying HTTP connection is still held by the response object
			// to allow the response content to be streamed directly from the network socket.
			// In order to ensure correct deallocation of system resources
			// the user MUST call CloseableHttpResponse#close() from a finally clause.
			// Please note that if response content is not fully consumed the underlying
			// connection cannot be safely re-used and will be shut down and discarded
			// by the connection manager.
			try {
				System.out.println(response1.getStatusLine());
				HttpEntity entity1 = response1.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				System.out.println(EntityUtils.toString(entity1, "UTF-8"));
				//				EntityUtils.consume(entity1);
			} finally {
				//				response1.close();
				HttpClientUtils.closeQuietly(response1); //包含EntityUtils.consume(entity1);
			}

			System.out.println("=================");

			HttpPost httpPost = new HttpPost("http://192.168.70.12:8080/bicycle/login");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("userName", "vip"));
			nvps.add(new BasicNameValuePair("password", "secret"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try {
				System.out.println(response2.getStatusLine());
				HttpEntity entity2 = response2.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				System.out.println(EntityUtils.toString(entity2));
				EntityUtils.consume(entity2);
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}

	}

}
