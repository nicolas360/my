package com.zhuc.my.mina.t4;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2013-4-9 下午1:40:59
 */
public class Client {

	private static Logger logger = LoggerFactory.getLogger(Client.class);
	private static String HOST = "127.0.0.1";
	private static int PORT = 3005;

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// 创建一个非阻塞的客户端程序
		IoConnector connector = new NioSocketConnector();
		// 设置链接超时时间
		connector.setConnectTimeout(30000);
		// 添加过滤器
		connector.getFilterChain().addLast("log", new LoggingFilter());
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new CmccSipcCodecFactory(Charset.forName("UTF-8"))));
		// 添加业务逻辑处理器类
		connector.setHandler(new ClientHandler());
		IoSession session = null;
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(HOST, PORT));// 创建连接

			future.awaitUninterruptibly();// 等待连接创建完成 (同步等待)
			session = future.getSession();// 获得session

			SmsObject o = new SmsObject();
			o.setSender("13601868870");
			o.setReceiver("13601860000");
			o.setMessage("你好 mina!");
			session.write(o);// 发送消息
		} catch (Exception e) {
			logger.error("客户端链接异常...", e);
		}
		session.getCloseFuture().awaitUninterruptibly();// 等待连接断开

		connector.dispose();
	}

}
