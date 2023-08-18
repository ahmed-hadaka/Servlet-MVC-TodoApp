package com.test.todo.servlets;

import java.io.IOException;
import java.sql.Connection;

import com.test.todo.beans.Todo;
import com.test.todo.dao.ApplicationDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddTodo")
public class AddTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/todo/add-todo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String title = request.getParameter("title");
		String description = request.getParameter("description");
		boolean status = request.getParameter("status").equals("true") ? true : false;
		String targetDate = request.getParameter("targetDate");

		String userName = (String) request.getSession().getAttribute("userName");

		Todo todo = new Todo(title, description, status, targetDate, userName);
		Connection connection = (Connection) getServletContext().getAttribute("dbconnection");

		int rows = ApplicationDao.addTodo(userName, todo, connection);

		// ----- Tracing purposes---------//
		if (rows == 0) {
			System.out.println("Error! Todo wasn't add");
		} else {
			System.out.println("Todo added successfully!");
		}

		response.sendRedirect(request.getContextPath() + "/RegisterUser");
	}

}
