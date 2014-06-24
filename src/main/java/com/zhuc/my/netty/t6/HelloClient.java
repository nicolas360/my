package com.zhuc.my.netty.t6;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Netty 客户端代码
 *
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.coderli.com
 */
public class HelloClient {

	private static final Logger logger = LoggerFactory.getLogger(HelloClient.class);

	private static ChannelFuture cf;

	private static ClientBootstrap bootstrap;

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		// Client服务启动器
		bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));
		// 设置一个处理服务端消息和各种消息事件的类(Handler)
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new HelloClientHandler());
			}
		});

		bootstrap.setOption("remoteAddress", new InetSocketAddress("localhost", 8000));

		// 连接到本地的8000端口的服务端
		cf = bootstrap.connect();

		cf.addListener(new ChannelFutureListener() {

			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				new Thread(new Runnable() {

					@Override
					public void run() {
						while (true) {
							try {
								Thread.sleep(8 * 1000l);

								logger.debug("status: " + cf.getChannel().isConnected());

								ChannelBuffer buffer = ChannelBuffers.buffer(2);
								buffer.writeBytes("ab".getBytes());
								cf.getChannel().write(buffer);

								//						if (!cf.getChannel().isConnected()) {
								//							logger.error("服务器尼玛敢关我？我重连！");
								//						}

							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		});

		logger.debug("客户端连接到服务器成功！");

	}

	private static class HelloClientHandler extends SimpleChannelHandler {

		/**
		 * 当绑定到服务端的时候触发，打印"Hello world, I'm client."
		 *
		 * @alia OneCoder
		 * @author lihzh
		 */
		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
			logger.debug("Hello world, I'm client.");
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
			logger.error("client exceptionCaught", e.getCause());
		}

		@Override
		public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			logger.debug("client receive channelDisconnected");
		}

		@Override
		public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			logger.debug("client receive channelClosed");

			ctx.getChannel().close();

			cf = bootstrap.connect();
			cf.addListener(new ChannelFutureListener() {

				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					logger.debug("哈哈， 我又来了!");

					String msg = "Hello, 我是重连机制.";
					ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
					buffer.writeBytes(msg.getBytes());
					future.getChannel().write(buffer);
				}
			});

		}

	}
}