package com.zhuc.my.mina.t1;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2013-4-9 下午1:40:59
 */
public class Client2 {

	private static Logger logger = LoggerFactory.getLogger(Client2.class);
	private static String HOST = "127.0.0.1";
	private static int PORT = 3005;

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// 创建一个非阻塞的客户端程序
		final IoConnector connector = new NioSocketConnector();
		// 设置链接超时时间
		connector.setConnectTimeout(30000);
		// 添加过滤器
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"), LineDelimiter.WINDOWS
						.getValue(), LineDelimiter.WINDOWS.getValue())));
		// 添加业务逻辑处理器类
		connector.setHandler(new Client1Handler());
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(HOST, PORT));// 创建连接

			// 连接完成时事件(异步)
			future.addListener(new IoFutureListener<ConnectFuture>() {

				@Override
				public void operationComplete(ConnectFuture future) {
					try {
						Thread.sleep(2000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					IoSession session = future.getSession();
					session.write("你好mina 我是异步程序");// 发送消息

					connector.dispose();
				}
			});

		} catch (Exception e) {
			logger.error("客户端链接异常...", e);
		}

	}

}
