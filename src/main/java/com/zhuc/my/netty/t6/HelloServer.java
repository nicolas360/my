package com.zhuc.my.netty.t6;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Netty 服务端代码
 *
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.coderli.com
 */
public class HelloServer {

	private static final Logger logger = LoggerFactory.getLogger(HelloServer.class);

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		// Server服务启动器
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// 设置一个处理客户端消息和各种消息事件的类(Handler)
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new HelloServerHandler());
			}
		});
		// 开放8000端口供客户端访问。
		bootstrap.bind(new InetSocketAddress(8000));

		logger.debug("服务器断绑定8000成功！");

	}

	private static class HelloServerHandler extends SimpleChannelHandler {

		/**
		 * 当有客户端绑定到服务端的时候触发，打印"Hello world, I'm server."
		 *
		 * @alia OneCoder
		 * @author lihzh
		 */
		@Override
		public void channelConnected(final ChannelHandlerContext ctx, ChannelStateEvent e) {
			logger.debug("Hello world, I'm server.");

		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
			logger.error("server exceptionCaught", e.getCause());
		}

		@Override
		public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			logger.debug("server receive channelDisconnected");
		}

		@Override
		public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			logger.debug("server receive channelClosed");
		}

		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
			ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
			logger.debug("服务器端收到内容为: " + buffer.toString(Charset.defaultCharset()));

			if (new Random().nextInt(2) == 1) {
				logger.debug("你中奖了, 我要关了你");
				ctx.getChannel().close();
			} else {
				logger.debug("你很幸运！");
			}

			logger.debug("\n");
		}

	}
}
