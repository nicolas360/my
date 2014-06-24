package com.zhuc.my.mina.t1;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2013-4-9 上午10:02:09
 */
public class Server1Handler extends IoHandlerAdapter {

	/**
	 * 
	 */
	public static Logger logger = LoggerFactory.getLogger(Server1Handler.class);

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("服务端与客户端创建连接..." + session.getRemoteAddress());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("服务端与客户端连接打开...");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.info("server receive: " + message.getClass());
		String msg = message.toString();
		logger.info("服务端接收到的数据为：" + msg);
		if ("bye".equals(msg)) { // 服务端断开连接的条件
			session.close();
		}
		Date date = new Date();
		session.write(date);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		//		session.close();	//短连接
		logger.info("服务端发送信息成功...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("服务端与客户端连接关闭...");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		logger.info("服务端进入空闲状态...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.error("服务端发送异常...", cause);
	}

}
