package com.zhuc.my.netty.t1;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.DynamicChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
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

	private static ChannelGroup allChannels = new DefaultChannelGroup();

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

		private static Map<Integer, Channel> map = new ConcurrentHashMap<Integer, Channel>();

		/**
		 * 当有客户端绑定到服务端的时候触发，打印"Hello world, I'm server."
		 *
		 * @alia OneCoder
		 * @author lihzh
		 */
		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
			logger.debug("Hello world, I'm server.");
		}

		/* (non-Javadoc)
		 * @see org.jboss.netty.channel.SimpleChannelHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
		 */
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
			ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
			logger.info(e.getChannel().getId() + " : " + buffer.toString(Charset.defaultCharset()));

			map.put(e.getChannel().getId(), e.getChannel());
			allChannels.add(e.getChannel());

			String msg = "server send: " + buffer.toString(Charset.defaultCharset()) + "\n";
			ChannelBuffer writeBuffer = new DynamicChannelBuffer(msg.getBytes().length);
			writeBuffer.writeBytes(msg.getBytes());

			ctx.getChannel().write(writeBuffer);
		}

		/* (non-Javadoc)
		 * @see org.jboss.netty.channel.SimpleChannelHandler#exceptionCaught(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.ExceptionEvent)
		 */
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
			logger.error("exceptionCaught", e);
		}

		/* (non-Javadoc)
		 * @see org.jboss.netty.channel.SimpleChannelHandler#channelClosed(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.ChannelStateEvent)
		 */
		@Override
		public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			logger.info("channelClosed");
		}

	}
}
