package com.zhuc.my.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

public class Test5 {
	public static void main(String[] args) throws Exception {
		Server server = Server.createTcpServer(new String[] { "-tcpPort", "9080" });
		server.start();
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:./h2db/sample", "sa", "");
		Statement stat = conn.createStatement();
		// stat.execute("DROP TABLE TIMER IF EXISTS");
		// stat.execute("CREATE TABLE TIMER(ID INT PRIMARY KEY, TIME VARCHAR)");
		System.out.println("Execute this a few times: SELECT TIME FROM TIMER");
		try {
			stat.execute("MERGE INTO TIMER VALUES(1, NOW())");
			ResultSet rset = stat.executeQuery("select id,TIME from TIMER");
			while (rset.next()) {
				System.out.println(rset.getString("id") + "  " + rset.getString("time"));
			}
			rset.close();
			Thread.sleep(10);
		} catch (SQLException e) {
			System.out.println("Error:" + e.toString());
		}
		stat.close();
		conn.close();
		server.stop();
	}
}
