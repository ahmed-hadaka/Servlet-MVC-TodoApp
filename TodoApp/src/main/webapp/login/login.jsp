<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="../Error.jsp"%>
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
		<h1>User Login Form</h1>

		<form action="LoginUser" method="POST" class="form">
			<p>user Name:</p>
			<input type="text" name="userName" placeholder="enter user name"
				required>

			<p>password:</p>
			<input type="password" name="password"
				placeholder="enter the password" required>

			<button type="submit">Submit</button>

		</form>
	</div>

	<!-- start user register form -->

	<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>