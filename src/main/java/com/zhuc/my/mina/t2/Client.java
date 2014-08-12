package com.zhuc.my.mina.t2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class Client {

	private static final int PORT = 8080;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		//客户端的实现
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		connector.getFilterChain().addLast("logging", new LoggingFilter());
		FileUploadClientHandler h = new FileUploadClientHandler();
		connector.setHandler(h);
		//本句需要加上，否则无法调用下面的readFuture来从session中读取到服务端返回的信息。
		connector.getSessionConfig().setUseReadOperation(true);

		ConnectFuture cf = connector.connect(new InetSocketAddress("localhost", PORT));

		IoSession session;
		//等待连接成功
		cf.awaitUninterruptibly();
		session = cf.getSession();

		System.out.println("client send begin");

		//传递文件开始
		String fileName = "test.jpg";
		FileInputStream fis = new FileInputStream(new File(fileName));
		byte[] a = new byte[1024 * 4];
		FileUploadRequest request = new FileUploadRequest();
		request.setFilename(fileName);
		request.setHostname("localhost");
		while (fis.read(a, 0, a.length) != -1) {
			request.setFileContent(a);
			//像session中写入信息供服务端获得
			session.write(request);
		}
		//发送完成的标志
		session.write(new String("finish"));

		System.out.println("client send finished and wait success");
		//接上面来取得服务端的信息
		Object result = session.read().awaitUninterruptibly().getMessage();
		if (result.equals("success")) {
			System.out.println("success!");
			//关闭客户端
			connector.dispose();
		}
	}
}