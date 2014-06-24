package com.zhuc.my.mina.t4;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2013-4-9 下午1:46:13
 */
public class ClientHandler extends IoHandlerAdapter {
	private static Logger logger = LoggerFactory.getLogger(ClientHandler.class);

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.info("client receive: " + message.getClass());
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.error("客户端发生异常...", cause);
	}
}
