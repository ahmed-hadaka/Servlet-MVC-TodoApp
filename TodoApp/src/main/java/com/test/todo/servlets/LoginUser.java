package com.test.todo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.test.todo.beans.Todo;
import com.test.todo.dao.ApplicationDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginUser")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");

		if (userName == null) { // check if the user register

			String notify = "<html><h3>Please Login Here </h3></html>";

			if (session.getAttribute("message") != null)
				response.getWriter().write((String) session.getAttribute("message"));

			response.getWriter().write(notify);

			request.getRequestDispatcher("/login/login.jsp").include(request, response);

		} else {
			Connection connection = (Connection) getServletContext().getAttribute("dbconnection");
			List<Todo> todos = ApplicationDao.getTodos(userName, connection);

			request.setAttribute("ListTodos", todos);

			request.getRequestDispatcher("/todo/todo-list.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get users todos from db
		Connection connection = (Connection) getServletContext().getAttribute("dbconnection");
		String userName = request.getParameter("userName");
		List<Todo> todos = ApplicationDao.getTodos(userName, connection);

		// put username in the session scope
		request.getSession().setAttribute("userName", userName);

		// put todos in request scope
		request.setAttribute("ListTodos", todos);

		// SendRedirect (PRG) PATTERN
		response.sendRedirect(request.getContextPath() + "/LoginUser");
	}

}
