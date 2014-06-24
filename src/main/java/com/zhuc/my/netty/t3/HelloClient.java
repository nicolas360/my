package com.zhuc.my.netty.t3;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;
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

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		// Client服务启动器
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// 设置一个处理服务端消息和各种消息事件的类(Handler)
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new ObjectEncoder(), new ObjectClientHandler());
			}
		});
		// 连接到本地的8000端口的服务端
		bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));

		logger.debug("客户端连接到服务器成功！");
	}

	/**
	 * 对象传递，客户端代码
	 * 
	 * @author lihzh
	 * @alia OneCoder
	 * @blog http://www.it165.net
	 */
	private static class ObjectClientHandler extends SimpleChannelHandler {

		/**
		 * 当绑定到服务端的时候触发，给服务端发消息。
		 * 
		 * @author lihzh
		 * @alia OneCoder
		 */
		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
			// 向服务端发送Object信息
			sendObject(e.getChannel());
		}

		/**
		 * 发送Object
		 * 
		 * @param channel
		 * @author lihzh
		 * @alia OneCoder
		 */
		private void sendObject(Channel channel) {
			Command command = new Command();
			command.setActionName("Hello action.");
			channel.write(command);
		}

	}
}