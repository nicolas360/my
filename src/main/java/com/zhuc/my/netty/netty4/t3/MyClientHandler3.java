package com.zhuc.my.netty.netty4.t3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.ThreadLocalRandom;

import org.joda.time.DateTime;
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
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		logger.debug("client channelActive");

		//		ByteBuf buf = Unpooled.buffer();
		//		buf.writeBytes(new byte[] { (byte) 0xfd, (byte) 0xdf });
		//		buf.writeInt(9);
		//		buf.writeBytes("hello".getBytes());
		//		buf.writeBytes(new byte[] { (byte) 0xdf, (byte) 0xfd });
		//
		//		// 写完立即关闭
		//		ctx.writeAndFlush(buf).addListener(ChannelFutureListener.CLOSE);

		ctx.writeAndFlush("hello123".getBytes());//.addListener(ChannelFutureListener.CLOSE);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#userEventTriggered(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent ise = (IdleStateEvent) evt;
			System.out.println("心跳:" + new DateTime() + ":" + ise.state());
			ctx.writeAndFlush(("heart" + ThreadLocalRandom.current().nextInt()).getBytes());
		}
	}

}
