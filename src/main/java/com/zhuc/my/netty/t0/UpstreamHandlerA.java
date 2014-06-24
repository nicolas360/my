package com.zhuc.my.netty.t0;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

@ChannelPipelineCoverage("all")
public class UpstreamHandlerA extends SimpleChannelUpstreamHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		System.out
				.println("UpstreamHandlerA.messageReceived:" + e.getMessage());
		super.messageReceived(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		System.out.println("UpstreamHandlerA.exceptionCaught:" + e.toString());
		e.getChannel().close();
	}
}