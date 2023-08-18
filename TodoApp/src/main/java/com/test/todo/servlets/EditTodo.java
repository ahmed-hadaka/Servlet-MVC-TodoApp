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

@WebServlet("/EditTodo")
public class EditTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get todo details from db using id
		Connection connection = (Connection) getServletContext().getAttribute("dbconnection");
		int todoId = Integer.parseInt(request.getParameter("todoId"));

		Todo todo = ApplicationDao.getTodo(todoId, connection);

		request.getSession().setAttribute("todoId", todoId);

		request.setAttribute("todo", todo);

		request.getRequestDispatcher("/todo/edit-todo.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get todo details
		Connection connection = (Connection) getServletContext().getAttribute("dbconnection");

		int id = (int) request.getSession().getAttribute("todoId");
		String userName = (String) request.getSession().getAttribute("userName");

		String title = request.getParameter("title");
		String description = request.getParameter("description");
		boolean status = request.getParameter("status").equals("true") ? true : false;
		String targetDate = request.getParameter("targetDate");

		Todo todo = new Todo(id, title, description, status, targetDate, userName);

		// update it in db
		int rows = ApplicationDao.updateTodo(todo, connection);

		if (rows == 0) {
			System.out.println("Error! can't edit the todo");
		} else {
			System.out.println("todo updated successfully!");
		}

		// redirect him back to RegisterUser servlet
		response.sendRedirect(request.getContextPath() + "/RegisterUser");

	}

}
