package com.zhuc.my.httpclient.t1;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

/**
 * @author zhuc
 * @create 2013-6-18 下午5:05:32
 */
public class Test1 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		HttpClient httpClient = new DefaultHttpClient();

		//		HttpGet httpGet = new HttpGet("http://localhost:8080/my/servlet/HttpClientServlet1?s=abc123");
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("localhost:8080").setPath("/my/servlet/HttpClientServlet1")
				.setParameter("s", "123cba");
		URI uri = builder.build();
		HttpGet httpGet = new HttpGet(uri);
		System.out.println(httpGet.getURI());

		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 1000);

		HttpResponse response = httpClient.execute(httpGet);
		System.out.println(response.getProtocolVersion());
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());

		HttpEntity entity = response.getEntity();
		if (null != entity) {
			String result = EntityUtils.toString(entity);
			System.out.println(result);
		}
	}

}
