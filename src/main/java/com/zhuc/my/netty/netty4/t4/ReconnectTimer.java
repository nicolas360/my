package com.zhuc.my.netty.netty4.t4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 断线重连定时器
*
* @author BeiTown
* @date 2014-5-16
*/
public class ReconnectTimer implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ReconnectTimer.class);

	@Override
	public void run() {
		logger.debug("正在尝试与服务器连接......");
		try {
			new NettyClient("221.130.184.32", 10101).run();
			logger.debug("与服务器断开连接，尝试10秒后与服务器重新连接......");
		} catch (Exception e) {
			logger.error("无法连接到服务器，尝试10秒后与服务器重新连接......", e);
		}
	}
}