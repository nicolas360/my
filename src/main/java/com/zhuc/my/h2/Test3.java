package com.zhuc.my.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test3 {
	public void runInsertDelete() {
		try {
			String sourceURL = "jdbc:h2:./h2db/sample";// H2 database
			String user = "sa";
			String key = "";
			try {
				Class.forName("org.h2.Driver");// H2 Driver
			} catch (Exception e) {
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(sourceURL, user, key);
			Statement stmt = conn.createStatement();
			stmt.execute("DROP TABLE if EXISTS mytable");
			stmt.execute("CREATE TABLE mytable(name VARCHAR(100),sex VARCHAR(10))");
			stmt.executeUpdate("INSERT INTO mytable VALUES('Steven Stander','male')");
			stmt.executeUpdate("INSERT INTO mytable VALUES('Elizabeth Eames','female')");
			stmt.executeUpdate("DELETE FROM mytable WHERE sex='male'");
			ResultSet rset = stmt.executeQuery("select name,sex from mytable");
			while (rset.next()) {
				System.out.println(rset.getString("name") + "  " + rset.getString("sex"));
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
	}

	public static void main(String[] args) {
		new Test3().runInsertDelete();
	}
}
