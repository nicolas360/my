package com.zhuc.my.netty.t4;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Netty 客户端代码
 *
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.coderli.com
 */
public class StringClient {

	private static final Logger logger = LoggerFactory.getLogger(StringClient.class);

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		StringClient client = new StringClient();
		client.con();
	}

	public void con() {
		// Client服务启动器
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// 设置一个处理服务端消息和各种消息事件的类(Handler)
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("encoder", new StringEncoder());
				pipeline.addLast("handler", new HelloClientHandler());
				pipeline.addLast("timeout", new IdleStateHandler(new HashedWheelTimer(), 0, 0, 10));
				pipeline.addLast("idleHandler", new ClientIdleHandler());
				return pipeline;
			}
		});

		try {
			Thread.sleep(new Random().nextInt(10) * 1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 连接到本地的8000端口的服务端
		ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
		future.addListener(new ChannelFutureListener() {

			@Override
			public void operationComplete(final ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					logger.debug("客户端连接到服务器成功！");

					//					ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
					//					service.scheduleAtFixedRate(new Runnable() {
					//
					//						@Override
					//						public void run() {
					//							String str = "hello 阿萨德" + new Date();
					//							future.getChannel().write(str);
					//						}
					//					}, 0, 5, TimeUnit.SECONDS);

				}
			}
		});
	}

	private class HelloClientHandler extends SimpleChannelHandler {

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

	}

	private class ClientIdleHandler extends IdleStateAwareChannelHandler {

		/* (non-Javadoc)
		 * @see org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler#channelIdle(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.handler.timeout.IdleStateEvent)
		 */
		@Override
		public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
			if (e.getState() == IdleState.ALL_IDLE) {
				logger.debug("链路空闲！发送心跳！S:{} - C:{} idleState:{}", new Object[] { ctx.getChannel().getRemoteAddress(),
						ctx.getChannel().getLocalAddress(), e.getState() });
				e.getChannel().write("我是客户端空闲了" + new Date());
				super.channelIdle(ctx, e);
			}
		}

	}
}