package com.zhuc.my.httpclient.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class HttpProxy4 {

	private final CloseableHttpClient httpclient = HttpClients.createDefault();

	private static final Logger logger = LoggerFactory.getLogger(HttpProxy4.class);

	public String accessGet(String uri) throws ClientProtocolException, IOException {
		String result = null;

		HttpGet httpGet = new HttpGet(uri);
		CloseableHttpResponse resp = httpclient.execute(httpGet);
		try {
			logger.debug("status: {}", new Object[] { resp.getStatusLine() });

			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");

			logger.debug("result========{}", new Object[] { result });
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			HttpClientUtils.closeQuietly(resp);
			HttpClientUtils.closeQuietly(httpclient);
		}

		return result;
	}

	public String accessPost(String uri, Map<String, String> map) throws ClientProtocolException, IOException {
		return accessPost(uri, getParamsFromMap(map));
	}

	private String accessPost(String uri, List<NameValuePair> nvps) throws ClientProtocolException, IOException {
		String result = null;

		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		CloseableHttpResponse resp = httpclient.execute(httpPost);
		try {
			logger.debug("status: {}", new Object[] { resp.getStatusLine() });
			for (NameValuePair nvp : nvps) {
				logger.debug("{}========{}", new Object[] { nvp.getName(), nvp.getValue() });
			}

			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");

			logger.debug("result========{}", new Object[] { result });
		} finally {
			HttpClientUtils.closeQuietly(resp);
			HttpClientUtils.closeQuietly(httpclient);
		}

		return result;
	}

	private List<NameValuePair> getParamsFromMap(Map<String, String> map) {
		List<NameValuePair> list = Lists.newArrayList();
		if (null != map) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}

		return list;
	}

}
