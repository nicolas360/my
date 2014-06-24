package com.zhuc.my.httpclient.t2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * httpclient 模拟表单上传文件和普通文字
 * @author zhuc
 * @create 2013-6-18 下午4:43:09
 */
public class CientTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://localhost:8080/my/servlet/MultipartServlet");
		FileBody bin = new FileBody(new File("e:\\测试.png"));
		StringBody comment = new StringBody("中国", Charset.forName("UTF-8"));
		MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null,
				Charset.forName("UTF-8")); //解决服务器断文件中文名的乱码问题, 服务器端是UTF-8编码
		//		MultipartEntity multipartEntity = new MultipartEntity();
		multipartEntity.addPart("upload", bin);
		multipartEntity.addPart("str", comment);

		post.setEntity(multipartEntity);
		HttpResponse response = httpClient.execute(post);
		if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity));
			EntityUtils.consume(entity);
		}

		httpClient.getConnectionManager().shutdown();
	}

}
