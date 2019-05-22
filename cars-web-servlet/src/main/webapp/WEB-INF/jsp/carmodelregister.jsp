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
<c:set var="count" value="0" scope="page" />
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="welcome">Gépjármű adatbázis</a>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="welcome">Főoldal</a></li>
				<li class="nav-item"><a class="nav-link" href="carregister">Gépjármű
						regisztráció</a></li>
				<li class="nav-item"><a class="nav-link active"
					href="carmodelregister">Gépjármű model regisztráció</a></li>
				<li class="nav-item"><a class="nav-link" href="logout">Kilépés</a></li>
			</ul>
		</div>
	</nav>
	<br />
	<div class="container">
		<div class="starter-template">
			<h3>${pageTitle}</h3>
			
			<%
			    Object error = request.getAttribute("error");
			    if (error != null && !error.toString().trim().isEmpty()) {
			        out.println("<div class=\"alert alert-danger\" role=\"alert\">"+error+"</div>");
			    }
			%>
			
			<form action="carmodelregister" th:object="${carModel}" method="post">

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="brand">Márka</label> <input type="text"
							class="form-control" name="brand" id="brand" placeholder="Márka"
							value="${carModel.brand}" required="required">
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="model">Model</label> <input type="text"
							class="form-control" name="model" id="model" placeholder="Model"
							value="${carModel.model}" required="required">
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="modelType">Model tipus</label> <input type="text"
							class="form-control" name="modelType" id="modelType"
							placeholder="Model tipus" value="${carModel.modelType}"
							required="required">
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="extra">Extrák</label> <input type="text"
							class="form-control" name="extra" id="extra" placeholder="Extrák"
							value="${carModel.extra}">
					</div>
				</div>

				<input type="hidden" name="id" value="${carModel.id}"> <input
					class="btn btn-primary" type="submit" value="Mentés" />
			</form>
		</div>
	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>
