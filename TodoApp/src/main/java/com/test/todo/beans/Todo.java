package com.test.todo.beans;

public class Todo {
	private int id;
	private String title;
	private String description;
	private boolean status;
	private String targetDate;
	private String userName;

	public Todo(int id, String title, String description, boolean status, String targetDate, String userName) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.targetDate = targetDate;
		this.userName = userName;
	}

	public Todo(String title, String description, boolean status, String targetDate, String userName) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.targetDate = targetDate;
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
