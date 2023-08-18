<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="../Error.jsp"%>
	<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Todo Management Application</title>

<link rel="stylesheet" href="css/styles.css">


</head>

<body>

	<jsp:include page="../common/header.jsp"></jsp:include>

	<!-- start user register form -->
	<div class="register-container">
		<h1 class="h1-class">User Register Form</h1>

		<form action="RegisterUser" method="POST" class="form">
			<p>First Name:</p>
			<input type="text" name="firstName" placeholder="enter first name"
				required>


			<p>last Name:</p>
			<input type="text" name="lastName" placeholder="enter last name"
				required>

			<p>user Name:</p>
			<input type="text" name="userName" placeholder="enter user name"
				required>

			<p>password:</p>
			<input type="password" name="password"
				placeholder="enter the password" required>

			<button type="submit">Submit</button>

		</form>
	</div>

	<!-- 	end user register-->
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>