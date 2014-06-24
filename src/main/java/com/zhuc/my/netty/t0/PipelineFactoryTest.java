package com.zhuc.my.netty.t0;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

public class PipelineFactoryTest implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("1", new UpstreamHandlerA());
		pipeline.addLast("2", new UpstreamHandlerB());
		pipeline.addLast("3", new DownstreamHandlerA());
		pipeline.addLast("4", new DownstreamHandlerB());
		pipeline.addLast("5", new UpstreamHandlerX());
		return pipeline;
	}

}