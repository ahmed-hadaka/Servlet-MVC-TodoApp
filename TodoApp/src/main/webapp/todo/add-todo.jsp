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

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	
    <div class="add-todo-container">
        <h1>Add New Todo</h1>

        <form action="AddTodo" method="POST">
            <p>Todo Title: </p>
            <input type="text" name="title" placeholder="enter cool title" class="input" required>

            <p>Todo Description: </p>
            <input type="text" name="description" placeholder="brief description" class="input"  required>

            <p>Todo Status: </p>
            <select name="status" class="input spec">
                <option value="false">In Progress</option>
                <option value="true">Complete</option>
            </select>

            <p>Todo Target Date: </p>
            <input type="date" name="targetDate" class="input" required>

            <button type="submit">Save </button>


        </form>

    </div>


	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
