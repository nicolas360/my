package com.zhuc.my.httpclient.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
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

	/**
	 * restful风格post请求
	 * @param uri
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public String restPost(String uri, String s) throws IOException {
		String result = null;

		HttpPost httpPost = new HttpPost(uri);
		StringEntity stringEntity = new StringEntity(s);
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);

		CloseableHttpResponse resp = httpclient.execute(httpPost);
		try {
			logger.debug("params: {}", new Object[]{s});
			logger.debug("status: {}", new Object[]{resp.getStatusLine()});

			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");

			logger.debug("result========{}", new Object[]{result});
		} finally {
			HttpClientUtils.closeQuietly(resp);
			HttpClientUtils.closeQuietly(httpclient);
		}

		return result;
	}

	public String accessPost(String uri, Object obj) throws ClientProtocolException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, IOException {
		return accessPost(uri, getParamsFromObject(obj));
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

	private List<NameValuePair> getParamsFromObject(Object obj) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		List<NameValuePair> list = Lists.newArrayList();

		if (null != obj) {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				String value = BeanUtils.getProperty(obj, field.getName());
				if (StringUtils.isNotBlank(value)) {
					list.add(new BasicNameValuePair(field.getName(), value));
				}

			}
		}

		return list;
	}

}
