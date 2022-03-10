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
			<c:choose>
			<c:when test="${monProfilUtilisateur==null}">
				<ul class="list-inline text-end">
					<li class="list-inline-item"><a class="text-end" href="<%=request.getContextPath()%>/ConnexionServlet">S'inscrire - Se connecter</a></li>
				</ul>	
			</c:when>
			<c:otherwise>
				<ul class="list-inline text-end">
					<li class="list-inline-item px-2"><a class="text-end" href="<%=request.getContextPath()%>/EncheresServlet">Enchères</a></li>
					<li class="list-inline-item px-2"><a class="text-end" href="<%=request.getContextPath()%>/VendreUnArticleServlet">Vendre un article</a></li>
					<li class="list-inline-item px-2"><a class="text-end" href="<%=request.getContextPath()%>/ProfilServlet?modifier="true"">Mon profil</a></li>
					<li class="list-inline-item"><a class="text-end" href="<%=request.getContextPath()%>/DeconnexionServlet">Se déconnecter</a></li>
				</ul>
			</c:otherwise>
			</c:choose>
		</div>
	</header>
	<section class="row justify-content-center">
		<h3 class="mt-5 text-center">Liste des enchères</h3>
		<form action="<%=request.getContextPath()%>/AccueilServlet" method="post">
			<div class="row mt-5">
				<h4>Filtres :</h4>
			</div>
			<div class="row mt-3 justify-content-center">
				<div class="col-8">
					<div class="row">
						<div class="col-10 mb-3">
							<div class="input-group">
								<span class="input-group-text"><i class="fas fa-search"></i></span>
								<input class="form-control" name="filtre" type="text" placeholder="Le nom de l'article contient" />
							</div>
						</div>
						<label class="col-3 col-form-label">Catégorie : </label>
						<div class="col-7">
							<select class="form-select" name="categorie" required>
								<option value="toutes" selected>Toutes</option>
								<c:forEach var="id" items="${listeCategories}">
								<option value="${id}">${id.libelle}</option>
								</c:forEach>
		 					 </select>
						</div>
					</div>
					<c:if test="${monProfilUtilisateur!=null}">
						<div class="row">
							<fieldset class="mt-4 col-5">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="choix" value="achats"
									onClick="encheresOuvertes.disabled=false; encheresEnCours.disabled=false; encheresRemportees.disabled=false;
										ventesEnCours.disabled=true; ventesNonDebutées.disabled=true; ventesTerminees.disabled=true" checked>
									<label class="form-check-label">Achats</label>
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="checkbox" id="encheresOuvertes" value="encheresOuvertes">
								  <label class="form-check-label">enchères ouvertes</label>
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="checkbox" id="encheresEnCours" value="encheresEnCours">
								  <label class="form-check-label">mes enchères en cours</label>
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="checkbox" id="encheresRemportees" value="encheresRemportees">
								  <label class="form-check-label">mes enchères remportées</label>
								</div>
							</fieldset>
							<fieldset class="mt-4 col-5">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="choix" value="ventes" 
									onClick="ventesEnCours.disabled=false; ventesNonDebutées.disabled=false; ventesTerminees.disabled=false;
										encheresOuvertes.disabled=true; encheresEnCours.disabled=true; encheresRemportees.disabled=true;">
									<label class="form-check-label">Mes ventes</label>
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="checkbox" id="ventesEnCours" value="ventesEnCours" disabled>
								  <label class="form-check-label">mes ventes en cours</label>
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="checkbox" id="ventesNonDebutées" value="ventesNonDebutées" disabled>
								  <label class="form-check-label">ventes non débutées</label>
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="checkbox" id="ventesTerminees" value="ventesTerminees" disabled>
								  <label class="form-check-label">ventes terminées</label>
								</div>
							</fieldset>
						</div>
					</c:if>
				</div>
				<div class="col-4">
					<div class="d-grid gap-2 col-10"><button type="submit" class="btn btn-primary" name ="submit" value="rechercher">Rechercher</button></div>
				</div>
			</div>
		</form>
	</section>
	
	<section>
		<div class="row gap-4 mt-5">
			<c:forEach var="id" items="${listeArticlesVendus}">
				<div class="col-5 border">
					<div class="row align-items-center">
						<div class="col-4 p-2">
							<img alt="Photo article" src="images/blank.jpg" width="100%">
						</div>
						<div class="col-8 p-2">
							<c:choose>
								<c:when test="${monProfilUtilisateur==null}">
									<c:out value="${id.nomArticle}"></c:out><br>
									Prix : <c:out value="${id.miseAPrix}"></c:out> points<br>
									Début de l'enchère : <c:out value="${id.dateDebutEncheres}"></c:out><br>
									Fin de l'enchère : <c:out value="${id.dateFinEncheres}"></c:out><br><br>
									Vendeur : <c:out value="${id.vendeur.pseudo}"></c:out>
								</c:when>
								<c:otherwise>
									<a class="h5" href="<%=request.getContextPath()%>/EnchereNonCommenceeServlet?noArticle=${id.noArticle}">
									<c:out value="${id.nomArticle}"></c:out><br></a>
									Prix : <c:out value="${id.miseAPrix}"></c:out> points<br>
									Début de l'enchère : <c:out value="${id.dateDebutEncheres}"></c:out><br>
									Fin de l'enchère : <c:out value="${id.dateFinEncheres}"></c:out><br><br>
									Vendeur : <a href="<%=request.getContextPath()%>/ProfilServlet?noVendeur=${id.vendeur.noUtilisateur}">
									<c:out value="${id.vendeur.pseudo}"></c:out></a>
								</c:otherwise>
							</c:choose>
							
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>