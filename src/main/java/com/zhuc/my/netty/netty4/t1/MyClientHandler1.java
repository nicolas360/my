package com.zhuc.my.netty.netty4.t1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2014-2-10 下午2:54:10
 */
public class MyClientHandler1 extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(MyClientHandler1.class);

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		logger.debug("client channelActive: " + ctx);
		//ctx.channel().writeAndFlush("[hello, 我是客户端1]");
		ctx.writeAndFlush("[hello, 我是客户端2]");
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelInactive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
		logger.debug("client channelInactive");

		logger.debug("开始重新连接");
		Client1.run();
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("client channelRead: " + msg);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		logger.debug("client channelReadComplete");
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.debug("client exceptionCaught");
	}

}
