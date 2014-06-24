package com.zhuc.my.httpclient.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * @author zhuc
 * @create 2013-6-19 上午10:54:30
 */
public class HttpProxy {

	private final HttpClient httpClient = new DefaultHttpClient();

	private static final Logger logger = LoggerFactory.getLogger(HttpProxy.class);

	/**
	 * get请求
	 * @param uri 
	 * @return 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public String accessGet(String uri) throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(uri);
		HttpResponse response = httpClient.execute(get);
		HttpEntity entity = response.getEntity();
		logger.debug("get request uri >>> " + uri);
		logger.debug("response status >>> " + response.getStatusLine().getStatusCode());

		String result = null;
		if (null != entity) {
			result = EntityUtils.toString(entity, "UTF-8");
			logger.debug("==================");
			logger.debug(result);
			logger.debug("==================\n");
		}

		return result;
	}

	/**
	 * post请求
	 * @param uri
	 * @param o 必须是javabean
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public String accessPost(String uri, Object o) throws ClientProtocolException, IOException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return accessPost(uri, getParams(o));
	}

	/**
	 * @param uri
	 * @param map HashMap
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public String accessPost(String uri, Map<String, String> map) throws ClientProtocolException, IOException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return accessPost(uri, getParamsFromMap(map));
	}

	/**
	 * post请求
	 * @param uri
	 * @param nvps
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String accessPost(String uri, List<NameValuePair> nvps) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(uri);
		if (!nvps.isEmpty()) {
			post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		}
		HttpResponse response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		logger.debug("post request uri >>> " + uri);
		for (NameValuePair nvp : nvps) {
			logger.debug(nvp.getName() + "========" + nvp.getValue());
		}
		logger.debug("");
		logger.debug("response status >>> " + response.getStatusLine().getStatusCode());

		String result = null;
		if (null != entity) {
			result = EntityUtils.toString(entity, "UTF-8");
			logger.debug("==================");
			logger.debug(result);
			logger.debug("==================\n");
		}

		return result;
	}

	/**
	 * @param o javabean
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private List<NameValuePair> getParams(Object o) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		List<NameValuePair> list = Lists.newArrayList();
		if (null != o) {
			Field[] fields = o.getClass().getDeclaredFields();
			for (Field field : fields) {
				String value = BeanUtils.getProperty(o, field.getName());
				BasicNameValuePair bnvp = new BasicNameValuePair(field.getName(), value);
				list.add(bnvp);
			}
		}

		return list;
	}

	/**
	 * @param map
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private List<NameValuePair> getParamsFromMap(Map<String, String> map) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		List<NameValuePair> list = Lists.newArrayList();
		if (null != map) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				BasicNameValuePair bnvp = new BasicNameValuePair(entry.getKey(), entry.getValue());
				list.add(bnvp);
			}
		}

		return list;
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		HttpClientUtils.closeQuietly(httpClient);
	}

}
