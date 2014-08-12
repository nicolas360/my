package com.zhuc.my.netty.t0;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.SimpleChannelDownstreamHandler;

public class DownstreamHandlerB extends SimpleChannelDownstreamHandler {
	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		System.out.println("DownstreamHandlerB.handleDownstream");
		super.handleDownstream(ctx, e);
	}
}