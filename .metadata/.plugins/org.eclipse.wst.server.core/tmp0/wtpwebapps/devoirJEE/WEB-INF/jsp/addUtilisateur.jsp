<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Projet</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/accueil.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
</head>

<body>
	<nav
		class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0  font-weight-bold"
			href="#">E-Projet</a> <span class="text-right text-white">Vous
			êtes connecter en tant que <c:out
				value="${sessionScope.utilisateur.getRole().getRoleName() }"></c:out>
		</span>
		<ul class="navbar-nav px-3">

			<li class="nav-item text-nowrap"><a class="nav-link"
				href="logout">Déconnexion</a></li>
		</ul>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link active" href="#">
								<span data-feather="home"></span>Bienvenue,&nbsp;<c:out
									value="${sessionScope.utilisateur.getPrenom() }"></c:out>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="accueil">
								<span data-feather="file"></span> Espace de Travail
						</a></li>
						<li class="nav-item"><a class="nav-link" href="profil"> <span
								data-feather="file"></span> Mon Profil
						</a></li>

					</ul>

				</div>
			</nav>

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
				<h1 class="h2">Ajouter Utilisateur</h1>
				<div class="btn-toolbar mb-2 mb-md-0"></div>
			</div>


			<br />
			<form action="AjouterUtilisateur" method="POST">
				<div class="form-row">

					<div class="form-group col-md-6">
						<label for="">Email</label> <input name="email" type="text"
							class="form-control" id="" required>
					</div>
					<div class="form-group col-md-6">
						<label for="">Mot de passe</label> <input name="motdepasse"
							type="password" class="form-control" id="" required>
					</div>

				</div>
				<div class="form-row">

					<div class="form-group col-md-3">
						<label for="">Nom</label> <input type="text" name="nom"
							class="form-control" placeholder="" id="" required>
					</div>
					<div class="form-group col-md-3">
						<label for="">Prenom</label> <input type="text" name="prenom"
							class="form-control" placeholder="" id="" required>
					</div>
					<div class="form-group col-md-4">
						<label for="">Role</label> <select id="" name="role"
							class="form-control" required>
							<option value="" selected>Choisir..</option>
							<option value="EMPLOYE">Employé</option>
							<option value="CHEF_DE_PROJET">Chef de Projet</option>
							<option value="ADMINISTRATEUR">Administrateur</option>
						</select>
					</div>

				</div>

				<button type="submit" class="btn btn-primary">Ajouter</button>
			</form>
			</main>
		</div>
	</div>
</body>
</html>