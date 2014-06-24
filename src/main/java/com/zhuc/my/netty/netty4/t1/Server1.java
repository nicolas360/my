package com.zhuc.my.netty.netty4.t1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2014-2-10 下午2:28:29
 */
public class Server1 {

	private static final Logger logger = LoggerFactory.getLogger(Server1.class);

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) {
		run();
	}

	/**
	 * 
	 */
	public static void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
			/*.handler(new LoggingHandler(LogLevel.INFO))*/.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(
							new LoggingHandler(LogLevel.INFO),
							//new LineBasedFrameDecoder(10000),
							new DelimiterBasedFrameDecoder(10000, false, new ByteBuf[] { Unpooled
									.wrappedBuffer(new byte[] { ']' }), }),
							new StringEncoder(Charset.forName("UTF-8")), new StringDecoder(Charset.forName("UTF-8")),
							new MyServerHandler1());

					// Delimiters.nulDelimiter(); // 默认的分隔符类Delimiters
				}
			});

			// Start the server.
			ChannelFuture f = b.bind(9000).sync();

			// Wait until the server socket is closed.
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			logger.error("", e);
		} finally {
			// Shut down all event loops to terminate all threads.
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
