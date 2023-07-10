package com.test.todo.filters;

import java.io.IOException;
import java.sql.Connection;

import com.test.todo.dao.ApplicationDoa;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class IdentificationFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = -1139372002937599355L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// ---- Pre-Processing -----//

		// filter if the user exist already in the DB

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		boolean flag = false;

		if (req.getMethod().equalsIgnoreCase("POST") && (req.getRequestURI().startsWith("/TodoApp/RegisterUser")
				|| req.getRequestURI().startsWith("/TodoApp/LoginUser"))) {
			Connection connection = (Connection) getServletContext().getAttribute("dbconnection");
			String userName = req.getParameter("userName");
			String password = req.getParameter("password");
			boolean isExist = ApplicationDoa.isExist(userName, password, connection);

			// trying to sinUp with existing credential
			if (req.getRequestURI().startsWith("/TodoApp/RegisterUser") && isExist) {
				String html = "<html><h3>This User Name is already exists!</h3></html>";
				req.getSession().setAttribute("message", html);
				res.sendRedirect(req.getContextPath() + "/LoginUser");
				flag = true;
			}

			// trying to login in without register
			if (req.getRequestURI().startsWith("/TodoApp/LoginUser") && !isExist) {
				String html = "<html><h3>There is No Account exists with this user Name!</h3></html>";
				res.getWriter().write(html + " ");
				req.getSession().setAttribute("message", html);
				res.sendRedirect(req.getContextPath() + "/RegisterUser");
				flag = true;
			}
		}

		if (!flag) { // if no problem
			chain.doFilter(req, res);
		}

		// ---- Post-Processing -----//
	}

}
