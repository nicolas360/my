package com.zhuc.my.netty.netty4.t2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2014-2-10 下午2:54:10
 */
public class MyClientHandler2 extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(MyClientHandler2.class);

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("client channelRead: " + msg);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("client channelActive");
		ctx.writeAndFlush(new My("zhuc", 20));

		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");

		ctx.writeAndFlush(list);
	}

}
