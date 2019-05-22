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
<c:set var="count2" value="0" scope="page" />
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="welcome">Gépjármű adatbázis</a>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link active" href="welcome">Főoldal</a></li>
				<li class="nav-item"><a class="nav-link" href="carregister">Gépjármű
						regisztráció</a></li>
				<li class="nav-item"><a class="nav-link"
					href="carmodelregister">Gépjármű model regisztráció</a></li>
				<li class="nav-item"><a class="nav-link" href="logout">Kilépés</a></li>
			</ul>
		</div>
	</nav>
	<br />
	<div style="margin: 10px">
		<div class="starter-template">
			<h3>Gépjármű lista</h3> 
			
			<%
			    Object carDeleteError = request.getAttribute("carDeleteError");
				Object carDeleteSuccessful = request.getAttribute("carDeleteSuccessful");
			    if (carDeleteError != null && !carDeleteError.toString().trim().isEmpty()) {
			        out.println("<div class=\"alert alert-danger\" role=\"alert\">"+carDeleteError+"</div>");
			    }else if (carDeleteSuccessful != null && !carDeleteSuccessful.toString().trim().isEmpty()){
			        out.println("<div class=\"alert alert-success\" role=\"alert\">"+carDeleteSuccessful+"</div>"); 
			    }
			%>

			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">#</th>
						<th scope="col">Márka</th>
						<th scope="col">Model</th>
						<th scope="col">Model Típus</th>
						<th scope="col">Extrák</th>
						<th scope="col">Tulajdonos</th>
						<th scope="col">Rendszám</th>
						<th scope="col">Alvázszám</th>
						<th scope="col">Gyártási év</th>
						<th scope="col">Forgalomba helyezés</th>
						<th scope="col"></th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cars}" var="item">
						<c:set var="count" value="${count + 1}" scope="page" />
						<tr>
							<th scope="row">${count}</th>
							<td>${item.carModelBrand}</td>
							<td>${item.carModel}</td>
							<td>${item.carModelType}</td>
							<td>${item.extra}</td>
							<td>${item.ownerName}</td>
							<td>${item.licensePlate}</td>
							<td>${item.VIN}</td>
							<td>${item.productionDate}</td>
							<td>${item.startTrafficDate}</td>
							<td>
								<form action="carRegisterEdit" method="post">
									<input type="hidden" name="carId" value="${item.id}" /> <input
										class="btn btn-outline-primary" type="submit" name="edit"
										value="Szerkesztés" />
								</form>
							</td>
							<td>
								<form action="DeleteCarServlet" method="post">
									<input type="hidden" name="carId" value="${item.id}" /> <input
										class="btn btn-outline-danger" type="submit" name="delete"
										value="Törlés" />
								</form>
							</td>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br />
	<div style="margin: 10px">
		<div class="starter-template">
			<h3>Gépjármű model lista</h3>
			
			<%
			    Object carModelDeleteError = request.getAttribute("carModelDeleteError");
				Object carModelDeleteSuccessful = request.getAttribute("carModelDeleteSuccessful");
			    if (carModelDeleteError != null && !carModelDeleteError.toString().trim().isEmpty()) {
			        out.println("<div class=\"alert alert-danger\" role=\"alert\">"+carModelDeleteError+"</div>");
			    }else if (carModelDeleteSuccessful != null && !carModelDeleteSuccessful.toString().trim().isEmpty()){
			        out.println("<div class=\"alert alert-success\" role=\"alert\">"+carModelDeleteSuccessful+"</div>"); 
			    }
			%>

			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">#</th>
						<th scope="col">Márka</th>
						<th scope="col">Model</th>
						<th scope="col">Model Típus</th>
						<th scope="col">Extrák</th>
						<th scope="col"></th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${carmodels}" var="item">
						<c:set var="count2" value="${count2 + 1}" scope="page" />
						<tr>
							<th scope="row">${count}</th>
							<td>${item.brand}</td>
							<td>${item.model}</td>
							<td>${item.modelType}</td>
							<td>${item.extra}</td>
							<td>
								<form action="carModelRegisterEdit" method="post">
									<input type="hidden" name="carModelId" value="${item.id}" /> <input
										class="btn btn-outline-primary" type="submit" name="edit"
										value="Szerkesztés" />
								</form>
							</td>
							<td>
								<form action="DeleteCarModelServlet" method="post">
									<input type="hidden" name="carModelId" value="${item.id}" /> <input
										class="btn btn-outline-danger" type="submit" name="delete"
										value="Törlés" />
								</form>
							</td>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>


	<script type="text/javascript"
		src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>
