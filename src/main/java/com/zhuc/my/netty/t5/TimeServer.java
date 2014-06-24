package com.zhuc.my.netty.t5;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.handler.codec.replay.VoidEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Netty 服务端代码
 *
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.coderli.com
 */
public class TimeServer {

	private static final Logger logger = LoggerFactory.getLogger(TimeServer.class);

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
				//				pipeline.addLast("decoder", new TimeDecoder());
				pipeline.addLast("decoder2", new TimeDecoder2());
				pipeline.addLast("handler", new TimeServerHandler());
				return pipeline;
			}
		});
		// 开放8000端口供客户端访问。
		bootstrap.bind(new InetSocketAddress(8000));

		logger.debug("服务器断绑定8000成功！");
	}

	private static class TimeServerHandler extends SimpleChannelHandler {

		private final ChannelBuffer buf = ChannelBuffers.dynamicBuffer();

		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
			ChannelBuffer m = (ChannelBuffer) e.getMessage();
			buf.writeBytes(m);

			// 如果有解码器 TimeDecoder 就不需要下面的判断位数了
			System.out.println("2:" + new Date(buf.readInt() * 1000L));

			buf.resetReaderIndex();
			System.out.println("3:" + new Date(buf.readInt() * 1000L));

			//			if (buf.readableBytes() >= 4) {
			//				long currentTimeMillis = buf.readInt() * 1000L;
			//				System.out.println("1:" + new Date(currentTimeMillis));
			//				e.getChannel().close();
			//			}
		}
	}

	private static class TimeDecoder extends FrameDecoder {

		@Override
		protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
			if (buffer.readableBytes() < 4) {
				return null;
			}
			return buffer.readBytes(4);
		}

	}

	private static class TimeDecoder2 extends ReplayingDecoder<VoidEnum> {

		@Override
		protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer, VoidEnum state) {
			return buffer.readBytes(4);
		}
	}
}
