package com.zhuc.my.mina.t2;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class FileUploadClientHandler extends IoHandlerAdapter {

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("client open");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("client session close");
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("thr result is" + message);
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.core.service.IoHandlerAdapter#exceptionCaught(org.apache.mina.core.session.IoSession, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.out.println("exceptionCaught");
		super.exceptionCaught(session, cause);
	}

}