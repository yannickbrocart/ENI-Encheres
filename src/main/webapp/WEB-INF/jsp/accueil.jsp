<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/encheres.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/all.css" rel="stylesheet">
<title>Accueil</title>
</head>
<body class="container-fluid col-10">
	<header class="row mt-3 align-items-center">
		<div id="logo" class="col-6 fs-2">
			<a href="<%=request.getContextPath()%>/">ENI-Enchères</a>
		</div>
		<div class="col-6">
			<% if (session.getAttribute("monProfilUtilisateur") == null) { %>
				<ul class="list-inline text-end">
					<li class="list-inline-item"><a class="text-end" href="<%=request.getContextPath()%>/ConnexionServlet">S'inscrire - Se connecter</a></li>
				</ul>	
			<% } else { %>
				<ul class="list-inline text-end">
					<li class="list-inline-item px-2"><a class="text-end" href="<%=request.getContextPath()%>/EncheresServlet">Enchères</a></li>
					<li class="list-inline-item px-2"><a class="text-end" href="<%=request.getContextPath()%>/VendreUnArticleServlet">Vendre un article</a></li>
					<li class="list-inline-item px-2"><a class="text-end" href="<%=request.getContextPath()%>/ProfilServlet?modifier="true"">Mon profil</a></li>
					<li class="list-inline-item"><a class="text-end" href="<%=request.getContextPath()%>/DeconnexionServlet">Se déconnecter</a></li>
				</ul>
			<% } %>
		</div>
	</header>
	<section class="row justify-content-center">
		<h3 class="mt-5 text-center">Liste des enchères</h3>
		<form action="<%=request.getContextPath()%>/AccueilServlet" method="post">
			<div class="row mt-5">
				<h4>Filtres :</h4>
			</div>
			<div class="row mt-3">
				<div class="col-6">
					<div class="row">
						<div class="col-10 mb-3">
							<div class="input-group">
								<span class="input-group-text"><i class="fas fa-search"></i></span>
								<input class="form-control" name="filtre" type="text" placeholder="Le nom de l'article contient" required />
							</div>
						</div>
						<label class="col-3 col-form-label">Catégorie : </label>
						<div class="col-7">
							<select class="form-select" name="categorie" required>
								<option selected>Choisir une catégorie...</option>
								<option value="1">Toutes</option>
								<option value="2">Informatique</option>
		                        <option value="3">Ameublement</option>
		                        <option value="4">Vêtement</option>
		                        <option value="5">Sport&Loisirs</option>
		 					 </select>
						</div>
					</div>
				</div>
				<div class="col-6">
					<div class="d-grid gap-2 col-10"><button type="submit" class="btn btn-primary" name ="submit">Rechercher</button></div>
				</div>
			</div>
		</form>
	</section>
	
	<section>
		<div class="row">
			<div class="col-5">
			
			</div>
			<div class="col-5">
			
			</div>
		</div>
		<div class="row">
			
		</div>
	</section>
</body>
</html>