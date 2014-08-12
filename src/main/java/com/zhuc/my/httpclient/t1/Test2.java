package com.zhuc.my.httpclient.t1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost("http://localhost:8080/my/servlet/HttpClientServlet1");
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("s", "postHello"));
		httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		String result = EntityUtils.toString(entity, "UTF-8");
		System.out.println(result);
	}

}
