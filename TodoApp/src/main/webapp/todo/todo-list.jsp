<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="../Error.jsp"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<html>
<head>
	<title>Todo Management Application</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="css/styles.css">
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	
	<!-- list todo area -->
	<div class="list-todo-container">
		<h1>List of Todos</h1>
		<hr>
		<a class="add-todo" href="AddTodo">Add Todo</a>
		<table class="table">
			<thead>
				<tr>
					<td>Title</td>
					<td>Target Date</td>
					<td>Todo Status</td>
					<td>Actions</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${requestScope.ListTodos != null}">

					<c:forEach var="todo" items="${requestScope.ListTodos}">

						<c:url var="editLink" value="EditTodo">
							<c:param name="todoId" value="${todo.id}"></c:param>
						</c:url>

						<c:url var="deleteLink" value="DeleteTodo">
							<c:param name="todoId" value="${todo.id}"></c:param>
						</c:url>

						<tr>
							<td>${todo.title}</td>
							<td>${todo.targetDate}</td>
							<td>${todo.status}</td>

							<td><a href="${editLink}">Edit</a> | <a href="${deleteLink}"
								onclick="if(!(confirm('Are you sure from deleting this Todo?')))return false;">Delete
							</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>

		</table>
	</div>
	<!-- list todo area -->

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
