package com.zhuc.my.jsoup;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单个帖子抓取
 * 
 * @author 王文成
 * @version 1.0
 * @since 2011-5-11
 */
public class Test2 {

	private static final Logger logger = LoggerFactory.getLogger(Test2.class);

	/**
	 * 抓取图片存放目录
	 */
	private static final String PIC_DIR = "E:/girl";

	/**
	 * 链接超时
	 */
	private static final int TIME_OUT = 5000;

	/**
	 * 处理帖子URL
	 * @param url
	 * @throws Exception
	 */
	static void go(String url) throws Exception {
		// JSOP创建链接
		Connection conn = Jsoup.connect(url);
		// 请求返回整个文档对象
		Document doc = conn.post();
		// 选择所有class=zoom 的img标签对象
		Elements imgs = doc.select("img");
		// 循环每个img标签
		for (int i = 0; i < imgs.size(); i++) {
			Element img = imgs.get(i);
			// 取得图片的下载地址
			String picURL = doc.baseUri() + img.attr("src");
			logger.info(picURL);
			// 保存图片
			save(picURL);
		}
	}

	//<img src="static/image/common/none.gif" file="data/attachment/forum/201105/08/174412nz3jq4z90s33s2t0.jpg" width="770" class="zoom" onclick="zoom(this, this.src)" id="aimg_180565" onmouseover="showMenu({'ctrlid':this.id,'pos':'12'})" alt="img_src_29620.jpg" title="img_src_29620.jpg" />
	//doc.select("img[class=zoom]")
	/**
	 * 保存图片
	 * @param url
	 * @param i
	 * @throws Exception
	 */
	static void save(String url) throws Exception {
		String fileName = url.substring(url.lastIndexOf("/"));
		String filePath = PIC_DIR + "/" + fileName;
		BufferedOutputStream out = null;
		byte[] bit = getByte(url);
		if (bit.length > 0) {
			try {
				out = new BufferedOutputStream(new FileOutputStream(filePath));
				out.write(bit);
				out.flush();
				logger.info("Create File success! [" + filePath + "]");
			} finally {
				if (out != null)
					out.close();
			}
		}
	}

	/**
	 * 获取图片字节流
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	static byte[] getByte(String uri) throws Exception {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);
		HttpGet get = new HttpGet(uri);
		get.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);
		try {
			HttpResponse resonse = client.execute(get);
			if (resonse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = resonse.getEntity();
				if (entity != null) {
					return EntityUtils.toByteArray(entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}
		return new byte[0];
	}

	public static void main(String[] args) throws Exception {
		// 开始抓取图片
		go("http://tieba.baidu.com/p/2391151593");
	}
}
