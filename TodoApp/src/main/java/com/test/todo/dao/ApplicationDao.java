package com.test.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.todo.beans.Todo;
import com.test.todo.beans.User;

public class ApplicationDao {

	public static boolean isExist(String userName, String password, Connection connection) {
		PreparedStatement statement = null;
		ResultSet res = null;
		boolean isExist = false;
		try {
			String query = "SELECT * FROM users WHERE user_name = ? AND password = ?";
			statement = connection.prepareStatement(query);

			statement.setString(1, userName);
			statement.setString(2, password);

			res = statement.executeQuery();

			while (res.next()) {
				isExist = true;
			}
			if (isExist)
				System.out.println("user exists!");
			else
				System.out.println("user Not exists!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}

	public static int addUser(User user, Connection connection) {
		PreparedStatement statement = null;
		int rows = 0;
		try {
			String query = "INSERT INTO users VALUES(?, ?, ?, ?)";
			statement = connection.prepareStatement(query);

			statement.setString(1, user.getUserName());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getPassword());

			rows = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rows;
	}

	public static List<Todo> getTodos(String userName, Connection connection) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Todo> todos = new ArrayList<>();

		try {
			String query = "SELECT * FROM todo WHERE user_name = ?";

			statement = connection.prepareStatement(query);
			statement.setString(1, userName);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				boolean status = resultSet.getBoolean("status");
				String targetDate = resultSet.getString("target_date");

				Todo todo = new Todo(id, title, description, status, targetDate, userName);
				todos.add(todo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todos;
	}

	public static int addTodo(String userName, Todo todo, Connection connection) {
		PreparedStatement statement = null;
		int rowsAffected = 0;

		try {
			String query = "INSERT INTO todo (title, description, status, target_date, user_name) VALUES(?, ?, ?, ?, ?)";

			statement = connection.prepareStatement(query);
			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getDescription());
			statement.setBoolean(3, todo.getStatus());
			statement.setString(4, todo.getTargetDate());
			statement.setString(5, todo.getUserName());

			rowsAffected = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public static Todo getTodo(int todoId, Connection connection) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Todo todo = null;

		try {
			String query = "SELECT * FROM todo WHERE id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, todoId);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				boolean status = resultSet.getBoolean("status");
				String targetDate = resultSet.getString("target_date");
				String userName = resultSet.getString("user_name");

				todo = new Todo(id, title, description, status, targetDate, userName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (todo == null) {
			System.out.println("Errot! can't fetch the todo");
		}
		return todo;
	}

	public static int updateTodo(Todo todo, Connection connection) {
		PreparedStatement statement = null;
		int rowsAffected = 0;

		try {
			String query = "UPDATE todo  SET title = ?, description = ?, status = ?, target_date = ?, user_name = ? WHERE id = ?";

			statement = connection.prepareStatement(query);
			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getDescription());
			statement.setBoolean(3, todo.getStatus());
			statement.setString(4, todo.getTargetDate());
			statement.setString(5, todo.getUserName());
			statement.setInt(6, todo.getId());

			rowsAffected = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public static int deleteTodo(int todoId, Connection connection) {
		PreparedStatement statement = null;
		int rowsAffected = 0;

		try {
			String query = "DELETE FROM todo WHERE id = ?";

			statement = connection.prepareStatement(query);
			statement.setInt(1, todoId);

			rowsAffected = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

}
