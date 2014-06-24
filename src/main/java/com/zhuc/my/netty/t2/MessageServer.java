package com.zhuc.my.netty.t2;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.coderli.com
 */
public class MessageServer {

	private static final Logger logger = LoggerFactory.getLogger(MessageServer.class);

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
				return Channels.pipeline(new MessageServerHandler());
			}
		});
		// 开放8000端口供客户端访问。
		bootstrap.bind(new InetSocketAddress(8000));

		logger.debug("服务器断绑定8000成功！");
	}

	private static class MessageServerHandler extends SimpleChannelHandler {

		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			logger.debug("channelConnected");
		}

		@Override
		public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			logger.debug("channelClosed");
		}

		/**
		 * 用户接受客户端发来的消息，在有客户端消息到达时触发
		 *
		 * @author lihzh
		 * @alia OneCoder
		 */
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
			ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
			logger.debug(buffer.toString(Charset.defaultCharset()));

			// 五位读取
			while (buffer.readableBytes() >= 5) {
				ChannelBuffer tempBuffer = buffer.readBytes(5);
				System.out.println(tempBuffer.toString(Charset.defaultCharset()));
			}

			/**
			 * 服务端是否分段发送并不会影响输出结果，也就是说，你一次性的把"Hi, I'm client."<br>
			 * 这段信息发送过来，输出的结果也是一样的。这就是开头说的，传输的是流，不分包。而只在于你如何分段读写<br>
			 */
			// 读取剩下的信息
			System.out.println(buffer.toString(Charset.defaultCharset()));

			e.getChannel().close();
		}

	}
}
