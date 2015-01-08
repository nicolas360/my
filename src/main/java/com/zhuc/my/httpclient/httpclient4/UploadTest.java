package com.zhuc.my.httpclient.httpclient4;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

public class UploadTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("xxx");
		HttpEntity httpEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
				.addPart("file", new FileBody(new File("xxx"))).setCharset(CharsetUtils.get("UTF-8")).build();

		httpPost.setEntity(httpEntity);
		HttpResponse resp = null;
		try {
			resp = httpclient.execute(httpPost);
			HttpEntity entity = resp.getEntity();
			if (null != entity) {
				EntityUtils.consume(entity);
			}

		} catch (Exception e) {

		} finally {
			HttpClientUtils.closeQuietly(resp);
			HttpClientUtils.closeQuietly(httpclient);
		}

	}

}
