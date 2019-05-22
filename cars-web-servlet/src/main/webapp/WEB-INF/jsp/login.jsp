<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<title>Gépjármű adatbázis</title>
<%@ page contentType="text/html; charset=UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="login">Gépjármű adatbázis</a>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="./login">Bejelentkezés
				</a></li>
				<li class="nav-item"><a class="nav-link" href="./registration">Regisztráció</a></li>
			</ul>
		</div>
	</nav>
	<br />
	<div class="container">
		<div class="starter-template">
			<h3>Bejelentkezés</h3>

			<%
			    Object loginError = request.getAttribute("loginError");
			    if (loginError != null && !loginError.toString().trim().isEmpty()) {
			        out.println("<div class=\"alert alert-danger\" role=\"alert\">Sikertelen belépés!</div>");
			    }
			%>

			<form action="./login" th:object="${login}" method="post">

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="usernameInput">Felhasználónév</label> <input
							type="text" class="form-control" name="username"
							id="usernameInput" placeholder="Felhasználónév" required>
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="passwordInput">Jelszó</label> <input type="password"
							class="form-control" name="password" id="passwordInput"
							placeholder="Jelszó" required>
					</div>
				</div>

				<input class="btn btn-primary" type="submit" value="Belépés" />
			</form>
		</div>
	</div>


	<script type="text/javascript"
		src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>
