package com.test.todo.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = -8950798734440303006L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String t = req.getParameter("todoId");
		int id = 0;
		if (t != null)
			id = Integer.parseInt(t);
		boolean flag = false;

		if (req.getMethod().equalsIgnoreCase("GET") && (req.getRequestURI().startsWith("/TodoApp/AddTodo")
				|| req.getRequestURI().startsWith("/TodoApp/EditTodo?todoId=" + id))) {

			HttpSession session = req.getSession();
			String userName = (String) session.getAttribute("userName");
			if (userName == null) {
				res.sendRedirect(req.getContextPath() + "/RegisterUser");
				flag = true;
			}
		}
		// pass the request along the filter chain
		if (!flag)
			chain.doFilter(req, response);
	}

}
