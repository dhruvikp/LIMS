package com.simplilearn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private Connection connection;

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/lims";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() throws SQLException {
		if (this.connection != null) {
			this.connection.close();
		}
	}
}
