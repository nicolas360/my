package com.zhuc.my.mina.t3;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.firewall.BlacklistFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.util.NoopFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;

public class Server {

	private static Logger logger = LoggerFactory.getLogger(Server.class);
	private static int PORT = 3005;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IoAcceptor acceptor = null;
		try {
			// 创建一个非阻塞的server端的Socket
			acceptor = new NioSocketAcceptor();
			// 设置过滤器（使用Mina提供的文本换行符编解码器）
			acceptor.getFilterChain().addLast("noop", new NoopFilter()); //这个过滤器什么也不做

			BlacklistFilter blacklistFilter = new BlacklistFilter();
			blacklistFilter.setBlacklist(ImmutableList.of(InetAddress.getByName("localhost")));
			acceptor.getFilterChain().addLast("black", blacklistFilter); //黑名单过滤器

			acceptor.getFilterChain().addLast("log", new LoggingFilter());
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
			// 设置读取数据的缓冲区大小
			acceptor.getSessionConfig().setReadBufferSize(2048);
			// 读写通道20秒内无操作进入空闲状态
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 20);
			// 绑定逻辑处理器
			acceptor.setHandler(new ServerHandler());
			// 绑定端口
			acceptor.bind(new InetSocketAddress(PORT));
			//			acceptor.bind(new InetSocketAddress(8888)); //可绑定多次
			logger.info("服务端启动成功... 端口号为：" + PORT);
		} catch (Exception e) {
			logger.error("服务端启动异常....", e);
			e.printStackTrace();
		}
	}
}
