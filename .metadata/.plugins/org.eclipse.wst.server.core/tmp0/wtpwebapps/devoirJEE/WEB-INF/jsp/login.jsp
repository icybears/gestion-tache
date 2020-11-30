<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connectez vous à E-Projet</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/login.css" rel="stylesheet" />
<link href="css/custom.css" rel="stylesheet">
</head>
<body class="body-img text-center">
<div id="overlay"></div>
	<form method="POST" action="" class="form-signin">
		<h1 class="h1 mb-3 font-weight-normal ">E-Projet</h1>
		<h1 class="h3 mb-3 font-weight-normal ">Authentification</h1>
		<c:if test="${requestScope.error }">
			<div class="alert alert-danger" role="alert">
				<c:out value="${requestScope.message}"></c:out>
			</div>
		</c:if>
		<label for="inputEmail" class="sr-only">Adresse Email</label> <input
			name="email" type="email" id="inputEmail" class="form-control"
			placeholder="Votre email" required autofocus> <label
			for="inputPassword" class="sr-only">Mot de passe</label> <input
			name="password" type="password" id="inputPassword"
			class="form-control" placeholder="Votre mot de passe" required>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
	</form>
	
</body>
</html>