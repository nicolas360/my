package com.zhuc.my.netty.netty4.t3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyClientEncoder extends MessageToByteEncoder<byte[]> {

	@Override
	protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out) throws Exception {
		out.writeBytes(new byte[] { (byte) 0xfd, (byte) 0xdf });
		out.writeInt(4 + msg.length);
		out.writeBytes(msg);
		out.writeBytes(new byte[] { (byte) 0xdf, (byte) 0xfd });
	}

}
