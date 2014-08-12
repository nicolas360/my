package com.zhuc.my.netty.netty4.t2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2014-2-10 下午2:54:17
 */
public class Client2 {

	private static final Logger logger = LoggerFactory.getLogger(Client2.class);

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) {
		run();
	}

	public static void run() {
		// Configure the client.
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO), new ObjectEncoder(),
									new ObjectDecoder(ClassResolvers.cacheDisabled(null)), new MyClientHandler2());
						}
					});

			// Start the client.
			ChannelFuture f = b.connect("localhost", 9000).sync();

			// Wait until the connection is closed.
			f.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			logger.error("", e);
		} finally {
			// Shut down the event loop to terminate all threads.
			group.shutdownGracefully();
		}
	}

}
