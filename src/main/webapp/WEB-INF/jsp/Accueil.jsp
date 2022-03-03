<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<title>Accueil</title>
</head>
<body class="container-fluid col-10">
	<header class="row mt-3">
		<a class="text-end" href="<%=request.getContextPath()%>/ConnexionServlet">S'inscrire - Se connecter</a>	
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
							<input class="form-control" name="filtre" type="search" placeholder="Le nom de l'artiste contient" required />
						</div>
						<label class="col-3 col-form-label">Catégorie : </label>
						<div class="col-7">
							<select class="form-control" name="categorie" required>
								<option>Toutes</option>
								<option>Informatique</option>
                                <option>Ameublement</option>
                                <option>Vêtement</option>
                                <option>Sport&Loisirs</option>
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