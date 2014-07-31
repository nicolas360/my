package com.zhuc.my.netty.netty4.t4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClient {
	private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

	private final String host;
	private final int port;

	public Channel channel;

	public NettyClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() {
		// Configure the client.
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO), new NettyHandler());
						}
					});

			// Start the client.	
			ChannelFuture f = b.connect(host, port).sync(); //运行后会在这里阻塞, 连接成功往下运行, 若失败则抛出异常退出
			logger.debug("1");

			channel = f.channel();

			// Wait until the connection is closed.
			channel.closeFuture().sync(); //运行后会在这里阻塞, 直到通道关闭才会往下走
			logger.debug("2");
		} catch (Exception e) {
			logger.error("服务器连接失败", e);

		} finally {
			// Shut down the event loop to terminate all threads.
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		while (true) {
			// 利用sync()阻塞特性, 这里可以死循环重连
			logger.debug("开始连接！");
			NettyClient nc = new NettyClient("192.168.70.12", 10100);
			nc.run(); //到这里阻塞

			try {
				TimeUnit.SECONDS.sleep(5l);
			} catch (InterruptedException e) {
				logger.error("", e);
			}
		}

	}
}