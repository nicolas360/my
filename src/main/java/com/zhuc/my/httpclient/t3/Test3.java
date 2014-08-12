package com.zhuc.my.httpclient.t3;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.zhuc.my.httpclient.utils.HttpProxy;

/**
 * 工具类测试
 * @author zhuc
 * @create 2013-6-19 下午1:31:20
 */
public class Test3 {

	private static final File f = new File("D:\\gaode.txt");

	private static final Logger logger = LoggerFactory.getLogger(Test3.class);

	private static final ExecutorService service = Executors.newFixedThreadPool(50);

	private static final LinkedBlockingQueue<String> queue = Queues.newLinkedBlockingQueue(100000);
	private static final LinkedBlockingQueue<String> filequeue = Queues.newLinkedBlockingQueue(100000);

	/**
	 * @param args
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws Exception {
		List<String> list = FileUtils.readLines(new File("D:\\GSM.csv"), "GBK");
		list.remove(0);

		for (String s : list) {
			filequeue.put(s);
		}

		final Pattern p = Pattern.compile("(\\d+),(\\d+),\\d+-\\d+,.+,(.+),.+");

		service.execute(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(30 * 1000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					List<String> fileList = Lists.newArrayList();
					queue.drainTo(fileList);

					writeFile(fileList);
				}
			}
		});

		for (int i = 0; i < 50; i++) {
			service.execute(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}

					String s = null;
					while (true) {
						try {
							s = filequeue.take();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						final Matcher m = p.matcher(s);

						if (m.matches()) {
							String address = m.group(3);
							try {
								String result = http("上海市" + address);

								My my = JSON.parseObject(result, My.class);
								StringBuilder sb = new StringBuilder();
								sb.append(m.group(1) + ",");
								sb.append(m.group(2) + ",");
								sb.append(my.getList().get(0).getX() + ":" + my.getList().get(0).getY() + ",");
								sb.append(address);

								queue.put(sb.toString());
							} catch (Exception e) {
								try {
									filequeue.put(s);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}

								logger.error("", e);
							}

						}
					}

				}
			});
		}

	}

	private static void writeFile(List<String> dataList) {
		try {
			FileUtils.writeLines(f, dataList, true);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	private static String http(String address) throws ClientProtocolException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, IOException {
		HttpProxy proxy = new HttpProxy();

		Map<String, String> map = Maps.newHashMap();
		map.put("key", "f6c97a7f64063cfee7c2dc2157847204d4dbf093934385f53d1bd0c1b8493e44d0dfd4c8e88a04bb");
		map.put("sid", "7000");
		map.put("encode", "UTF-8");
		map.put("resType", "json");
		map.put("address", address);

		String s = proxy.accessPost("http://192.168.51.43:8081/geocode/simple", map);
		proxy.close();

		return s;
	}

}
