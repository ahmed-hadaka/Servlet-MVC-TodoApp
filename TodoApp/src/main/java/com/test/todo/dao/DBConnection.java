package com.test.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection() {
		String jdbcURL = "jdbc:mysql://localhost:3306/todos";
		String jdcbUserName = "root";
		String jdcPassword = "mysql";

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdcbUserName, jdcPassword);

		} catch (ClassNotFoundException e) {
			// trace purposes
			System.out.println("where is your class file?");
			e.printStackTrace();

		} catch (SQLException e) {
			// trace purposes
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}

		// trace purposes
		if (connection != null) {
			System.out.println("Connection done successfully to the DB!");
		}

		return connection;
	}
}
