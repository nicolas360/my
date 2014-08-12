package com.zhuc.my.netty.netty4.t3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2014-2-10 下午2:54:10
 */
public class MyClientHandler3 extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(MyClientHandler3.class);

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

		ByteBuf buf = Unpooled.buffer();
		buf.writeBytes(new byte[] { (byte) 0xfd, (byte) 0xdf });
		buf.writeInt(9);
		buf.writeBytes("hello".getBytes());
		buf.writeBytes(new byte[] { (byte) 0xdf, (byte) 0xfd });

		ctx.writeAndFlush(buf);
	}

}
