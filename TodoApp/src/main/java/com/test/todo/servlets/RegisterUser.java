package com.test.todo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.test.todo.beans.Todo;
import com.test.todo.beans.User;
import com.test.todo.dao.ApplicationDoa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");

		if (userName == null) {
			String notify = "<html><h3>Please Register Here </h3></html>";

			if (session.getAttribute("message") != null)
				response.getWriter().write((String) session.getAttribute("message"));
			response.getWriter().write(notify);

			request.getRequestDispatcher("/register/register.jsp").include(request, response);
		} else {
			Connection connection = (Connection) getServletContext().getAttribute("dbconnection");
			List<Todo> todos = ApplicationDoa.getTodos(userName, connection);

			request.setAttribute("ListTodos", todos);

			// forward them
			request.getRequestDispatcher("/todo/todo-list.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get connection from the context scope
		Connection connection = (Connection) getServletContext().getAttribute("dbconnection");
		String userName = request.getParameter("userName");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		User user = new User(firstName, lastName, userName, password);

		// put it in a session scope
		request.getSession().setAttribute("userName", userName);

		// add our user to the data base
		int rows = ApplicationDoa.addUser(user, connection);

		// -------Tracing purposes--------//

		if (rows == 0) {
			System.out.println("sorry! an error occurs");

		} else {
			System.out.println("user register successfully");
		}

		// forward the user to the todo-list.jsp
		response.sendRedirect(request.getContextPath() + "/RegisterUser");
	}

}
