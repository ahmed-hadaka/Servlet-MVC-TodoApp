package com.test.todo.servlets;

import java.io.IOException;
import java.sql.Connection;

import com.test.todo.dao.ApplicationDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteTodo")
public class DeleteTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection connection = (Connection) getServletContext().getAttribute("dbconnection");
		int todoId = Integer.parseInt(request.getParameter("todoId"));

		int rowsAffected = ApplicationDao.deleteTodo(todoId, connection);

		if (rowsAffected == 0) {
			System.out.println("Error! Can't delete the todo");
		}

		response.sendRedirect(request.getContextPath() + "/RegisterUser");
	}

}
