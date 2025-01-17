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
		<a class="navbar-brand col-sm-3 col-md-2 mr-0  font-weight-bold" href="#">E-Projet</a> <span class="text-right text-white">Vous �tes connecter en tant que <c:out
						value="${sessionScope.utilisateur.getRole().getRoleName() }"></c:out></span>
		<ul class="navbar-nav px-3">
			
			<li class="nav-item text-nowrap"><a class="nav-link"
				href="logout">D�connexion</a></li>
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
						<li class="nav-item"><a class="nav-link" href="accueil"> <span
								data-feather="file"></span> Espace de Travail
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
				<h1 class="h2">Mon Espace de Travail</h1>
				<div class="btn-toolbar mb-2 mb-md-0">
					<div class="btn-group mr-2">
						
					</div>

				</div>
			</div>



			<br/>
			<c:if test="${param.status=='succes'}">
				<div class="alert alert-success" role="alert">
					L'op�ration a �t� effectuer avec succ�s.
				</div>
			</c:if>
				<c:if test="${param.status=='echec'}">
				<div class="alert alert-danger" role="alert">
					Echec de l'op�ration.
				</div>
			</c:if>
			<div class="table-responsive">
				<table class="table table-striped table-sm">
					<thead>
						<tr>
							<th>Numero</th>
							<th>Description</th>
							<th>Dur�e</th>
							<th>Date d�but</th>
							<th>Date fin</th>
							<th>Statut</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.taches}" var="tache">
							<tr>
								<td><c:out value="${tache.id}"></c:out></td>
								<td><c:out value="${tache.description}"></c:out></td>
								<td><c:out value="${tache.duree}"></c:out></td>
								<td><c:out value="${tache.dateDebut}"></c:out></td>
								<td><c:out value="${tache.dateFin}"></c:out></td>
								<td><c:out value="${tache.statut.getStatutName()}"></c:out></td>
								<td><c:url value="/ModifierTache" var="modifierTache">
										<c:param name="id" value="${tache.id }" />
									</c:url> <a href="${modifierTache }">Modifier</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</main>
		</div>
	</div>
</body>
</html>