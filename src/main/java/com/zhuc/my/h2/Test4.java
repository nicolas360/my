package com.zhuc.my.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test4 {
	public void query(String SQL) {
		try {
			//
			//String sourceURL = "jdbc:h2:h2/bin/mydb";
			String sourceURL = "jdbc:h2:./h2db/sample";
			String user = "sa";
			String key = "";

			try {
				Class.forName("org.h2.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(sourceURL, user, key);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(SQL);
			while (rset.next()) {
				System.out.println(rset.getString("name") + "  ");
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
	}

	public static void main(String[] args) {
		Test4 tt = new Test4();
		tt.query("select * from test");
	}
}
