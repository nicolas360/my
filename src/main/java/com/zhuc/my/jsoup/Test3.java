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
 * 获取整个论坛的图片
 * 
 * @author 王文成
 * @version 1.0
 * @since 2011-5-11
 */
public class Test3 {

	private static final Logger logger = LoggerFactory.getLogger(Test3.class);

	/**
	 * 抓取图片存放目录
	 */
	private static final String PIC_DIR = "D:/girl";

	/**
	 * 链接超时
	 */
	private static final int TIME_OUT = 10000;

	static void forum(String url) throws Exception {
		Connection conn = Jsoup.connect(url).timeout(TIME_OUT);
		Document doc = conn.post();
		//<a href="http://bbs.wed114.cn/thread-34918-1-1.html" onclick="atarget(this)" class="xst">◤我的美美婚纱照◥ 值得推荐~~~</a>
		Elements alinks = doc.select("img");
		for (int i = 0; i < alinks.size(); i++) {
			Element alink = alinks.get(i);
			// 取得帖子的名称
			String name = alink.text();
			// 取得帖子的链接
			String href = alink.attr("href");
			logger.info("正在抓取 [name=" + name + "][href=" + href + "]");
			try {
				// 处理帖子
				post(href);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 处理帖子URL
	 * @param url
	 * @throws Exception
	 */
	static void post(String url) throws Exception {
		// JSOP创建链接
		Connection conn = Jsoup.connect(url);
		// 请求返回整个文档对象
		Document doc = conn.post();
		// 选择所有class=zoom 的img标签对象
		Elements imgs = doc.select("img");
		// 循环每个img标签
		for (int i = 0; i < imgs.size(); i++) {
			// 不能请求的太快了...
			Thread.sleep(500);
			Element img = imgs.get(i);
			// 取得图片的下载地址
			final String picURL = doc.baseUri() + img.attr("src");
			logger.info(picURL);
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 保存图片
					try {
						save(picURL);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	/**
	 * 保存图片
	 * 
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
	 * 
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
		forum("http://tieba.baidu.com/p/2391665193");
	}
}
