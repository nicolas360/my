package com.zhuc.my.netty.t0;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class Client {

	public static void main(String[] args) {
		// Client服务启动器
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// 设置一个处理服务端消息和各种消息事件的类(Handler)
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new ClientHandler());
			}
		});
		// 连接到本地的8000端口的服务端
		ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8888));
		future.addListener(new ChannelFutureListener() {

			@Override
			public void operationComplete(final ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
					service.scheduleAtFixedRate(new Runnable() {

						@Override
						public void run() {
							String str = "hello 阿萨德" + new Date();
							ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
							buffer.writeBytes(str.getBytes());
							future.getChannel().write(buffer);
						}
					}, 0, 5, TimeUnit.SECONDS);

				}
			}
		});
	}

	private static class ClientHandler extends SimpleChannelHandler {

		/**
		 * 当绑定到服务端的时候触发，打印"Hello world, I'm client."
		 *
		 * @alia OneCoder
		 * @author lihzh
		 */
		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		}

	}
}
