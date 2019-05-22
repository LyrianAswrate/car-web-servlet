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
				<li class="nav-item"><a class="nav-link active"
					href="carregister">Gépjármű regisztráció</a></li>
				<li class="nav-item"><a class="nav-link"
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
			
			<form action="carregister" th:object="${car}" method="post">

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="carModelId">Model</label> <select
							class="custom-select" name="carModelId" id="carModelId"
							required="required">
							<c:forEach items="${carModels}" var="carmodel">
								<c:choose>
									<c:when test="${carmodel.id.equals(car.carModelId)}">
										<option value="${carmodel.id}" selected>${carmodel.brand}
											- ${carmodel.model} - ${carmodel.modelType}</option>
									</c:when>
									<c:otherwise>
										<option value="${carmodel.id}">${carmodel.brand}-
											${carmodel.model} - ${carmodel.modelType}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="ownerName">Tulajdonos</label> <input type="text"
							class="form-control" name="ownerName" id="ownerName"
							placeholder="Tulajdonos" value="${car.ownerName}"
							required="required">
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="licensePlate">Rendszám</label> <input type="text"
							class="form-control" name="licensePlate" id="licensePlate"
							placeholder="Rendszám" value="${car.licensePlate}">
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="VIN">Alvázszám</label> <input type="text"
							class="form-control" name="VIN" id="VIN" placeholder="Alvázszám"
							value="${car.VIN}" required="required">
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="productionDate">Gyártási év</label> <input type="date"
							class="form-control" name="productionDate" id="productionDate"
							placeholder="Gyártási év" value="${car.productionDate}"
							required="required">
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="startTrafficDate">Forgalomba helyezés</label> <input
							type="date" class="form-control" name="startTrafficDate"
							id="startTrafficDate" placeholder="Forgalomba helyezés"
							value="${car.startTrafficDate}">
					</div>
				</div>
				<input type="hidden" name="id" value="${car.id}">
				<input class="btn btn-primary" type="submit" value="Mentés" />
			</form>
		</div>
	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>
