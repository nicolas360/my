package com.zhuc.my.log4j.t1;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log4j中将日志发送到远程服务器
 * @author zhuc
 * @version 2013-6-23 下午9:05:46
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure(Client.class.getResource("log4j-client.properties"));

		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(new My(), 0, 5, TimeUnit.SECONDS);

	}

}

class My implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(My.class);

	@Override
	public void run() {
		logger.error("now: " + new Date());
	}
}