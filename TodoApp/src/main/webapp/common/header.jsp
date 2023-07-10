<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<style>
.header-container {
	background: #a2d2ff;
	padding: 20px 20px;
	display: flex;
	justify-content: space-between;
}

.logo {
	font: 1.5em "Fira Sans", sans-serif;
}

.link:hover {
	text-decoration: none;
	color: white;
}

.link {
	text-decoration: none;
	color: white;
}

.register a {
	font: 1em "Fira Sans", sans-serif;
	color: #e6e6e6;
}

.register {
	width: 100px;
	display: flex;
	justify-content: space-between;
}
</style>

<header>
	<div class="header-container">
		<a class="link logo" href="RegisterUser"> Todo App </a>
		<div class="register">
			<c:choose>
			
				<c:when test="${userName != null}">
			        <a class="link" href="LogoutServlet"> Logout </a>
			     </c:when>

				<c:otherwise>

					<a class="link" href="LoginUser"> Login </a>
					<a class="link" href="RegisterUser"> SingUp </a>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</header>


