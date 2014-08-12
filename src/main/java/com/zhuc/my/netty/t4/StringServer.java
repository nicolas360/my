package com.zhuc.my.netty.t4;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Netty 服务端代码
 *
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.coderli.com
 */
public class StringServer {

	private static final Logger logger = LoggerFactory.getLogger(StringServer.class);

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
				ChannelPipeline pipeline = Channels.pipeline();

				ExecutionHandler executionHandler = new ExecutionHandler(new OrderedMemoryAwareThreadPoolExecutor(16,
						1048576, 1048576));

				// 该解码器用于区分String流的界限, 10000为最大帧长度
				pipeline.addLast("frameDecoder", new DelimiterBasedFrameDecoder(10000, Delimiters.lineDelimiter()));
				// String解码器默认只能接收最大1024个字节内容， 超过该长度将会出现端包问题, 可以用上面的解码器来解决该问题
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("executionHandler", executionHandler);
				pipeline.addLast("handler", new HelloServerHandler());
				return pipeline;
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
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
			logger.debug("Hello world, I'm server.");
		}

		/* (non-Javadoc)
		 * @see org.jboss.netty.channel.SimpleChannelHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
		 */
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
			String str = (String) e.getMessage();
			logger.info(str);
		}
	}
}
