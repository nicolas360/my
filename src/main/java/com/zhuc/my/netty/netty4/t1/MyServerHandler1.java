package com.zhuc.my.netty.netty4.t1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2014-2-10 下午2:28:22
 */
public class MyServerHandler1 extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(MyServerHandler1.class);

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRegistered(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		logger.debug("server channelRegistered");
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		logger.debug("server channelActive");

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000l * (new Random().nextInt(10) + 1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ctx.close();
			}
		}).start();
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelInactive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("server channelInactive");
		//		ctx.close();
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("server channelRead: " + msg);

		if (msg instanceof String) {
			String m = (String) msg;
			logger.debug("m:" + m);

			if ("[bye]".equals(m)) {
				ctx.close();
				return;
			}

			ctx.writeAndFlush("服务器收到了: " + m);
		}

	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		logger.debug("server channelReadComplete");
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("server exceptionCaught", cause);
	}

}
