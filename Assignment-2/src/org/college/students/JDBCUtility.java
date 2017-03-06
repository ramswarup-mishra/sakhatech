package org.college.students;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtility {
	static Connection con = null;

	public static Connection get() throws SQLException, ClassNotFoundException {
		if (con == null) {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
		}
		return con;
	}
}
