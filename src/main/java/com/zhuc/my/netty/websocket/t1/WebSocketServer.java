package com.zhuc.my.netty.websocket.t1;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketServer {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	public static ChannelGroup recipients = new DefaultChannelGroup();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new WebSocketServerPipelineFactory());
		bootstrap.bind(new InetSocketAddress(10086));

		logger.debug("websocket服务器端监听启动");
	}

}
