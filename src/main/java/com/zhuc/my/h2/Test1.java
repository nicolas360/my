package com.zhuc.my.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test1 {

	private static final Logger logger = LoggerFactory.getLogger(Test1.class);

	private Server server;
	private final String port = "9080";
	private final String dbDir = "./h2db/sample";
	private final String user = "sa";
	private final String password = "";

	public void startServer() {
		try {
			logger.debug("正在启动h2...");
			server = Server.createTcpServer(new String[] { "-tcpPort", port }).start();
		} catch (Exception e) {
			logger.error("启动h2出错：", e);
		}
	}

	public void stopServer() {
		if (server != null) {
			logger.debug("正在关闭h2...");
			server.stop();
			logger.debug("关闭成功.");
		}
	}

	public void useH2() {
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:" + dbDir, user, password);
			Statement stat = conn.createStatement();
			// insert data
			//stat.execute("DROP TABLE IF EXISTS TEST");
			//			stat.execute("CREATE TABLE TEST(NAME VARCHAR)");
			stat.execute("INSERT INTO TEST VALUES('Hello World2')");
			// use data
			ResultSet result = stat.executeQuery("select name from test ");
			int i = 1;
			while (result.next()) {
				logger.debug(i++ + ":" + result.getString("name"));
			}
			result.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test1 h2 = new Test1();
		h2.startServer();
		h2.useH2();
		h2.stopServer();
		logger.debug("==END==");
	}
}
