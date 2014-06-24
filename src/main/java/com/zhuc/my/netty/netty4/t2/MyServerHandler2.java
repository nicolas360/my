package com.zhuc.my.netty.netty4.t2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2014-2-10 下午2:28:22
 */
public class MyServerHandler2 extends SimpleChannelInboundHandler<My> {

	private static final Logger logger = LoggerFactory.getLogger(MyServerHandler2.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, My msg) throws Exception {
		logger.debug("server channelRead0: " + msg);
	}

}
