package com.zhuc.my.netty.t0;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class UpstreamHandlerX extends SimpleChannelUpstreamHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		System.out.println("UpstreamHandlerX.messageReceived");
		e.getChannel().write(e.getMessage());// (2)
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		System.out.println("UpstreamHandlerX.exceptionCaught");
		e.getChannel().close();
	}
}