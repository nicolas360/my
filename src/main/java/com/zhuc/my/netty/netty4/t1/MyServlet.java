package com.zhuc.my.netty.netty4.t1;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServlet extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(MyServlet.class);

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		Client1.run();
		logger.debug("客户端启动连接");
	}

}
