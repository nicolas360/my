package com.zhuc.my.netty.netty4.t4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClient {
	private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

	private final String host;
	private final int port;

	public NettyClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws Exception {
		// Configure the client.
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO)
							//									new NettyHandler()
									);
						}
					});

			// Start the client.	
			ChannelFuture f = b.connect(host, port).sync();

			// Wait until the connection is closed.
			f.channel().closeFuture().sync();//运行后会在这里阻塞
		} catch (Exception e) {
			logger.error("netty连接失败", e);

		} finally {
			// Shut down the event loop to terminate all threads.
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
		service.scheduleAtFixedRate(new ReconnectTimer(), 1, 10, TimeUnit.SECONDS);
	}
}