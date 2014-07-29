package com.zhuc.my.netty.netty4.t3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2014-2-10 下午2:28:22
 */
public class MyServerHandler3 extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(MyServerHandler3.class);

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("server channelRead: " + msg);

		ByteBuf buf = (ByteBuf) msg;
		//		List<Byte> list = Lists.newArrayList();

		logger.debug("writerIndex: " + buf.writerIndex());

		//		while (buf.isReadable()) {
		//			list.add(buf.readByte());
		//		}

		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);

		StringBuilder sb = new StringBuilder();
		for (byte c : bytes) {
			sb.append(Integer.toHexString(0xff & c) + " ");
		}

		logger.debug("bytes: " + sb.toString());
	}

}
