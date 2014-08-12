package com.zhuc.my.netty.t0;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class ServerTest {

	public static void main(String args[]) {
		ServerBootstrap bootsrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

		bootsrap.setPipelineFactory(new PipelineFactoryTest());

		bootsrap.bind(new InetSocketAddress(8888));
	}
}