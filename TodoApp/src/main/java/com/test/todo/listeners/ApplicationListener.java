package com.test.todo.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import com.test.todo.dao.DBConnection;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		try {
			// tracing purposes
			System.out.println("In context Initialized!");
			Connection connection = DBConnection.getConnection();
			sce.getServletContext().setAttribute("dbconnection", connection);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// get connection object from app context scope
		Connection connection = (Connection) sce.getServletContext().getAttribute("dbconnection");

		// close it
		try {
			connection.close();
			System.out.println("Application Close successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
