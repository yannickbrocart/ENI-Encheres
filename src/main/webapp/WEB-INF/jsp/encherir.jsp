<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/encheres.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/all.css" rel="stylesheet">
<title>Enchérir</title>
</head>
<body class="container-fluid col-10">
	<header class="row mt-3 align-items-center">
		<div id="logo" class="col-6 fs-2">
			<a href="<%=request.getContextPath()%>/">ENI-Enchères</a>
		</div>
	</header>
	<div class="row justify-content-center">
		<h3 class="mt-5 text-center">Détail vente</h3>
	</div>
	<div class="row">
		<div class="col-4">
			<div class="mt-3">
				<img alt="Photo article" src="images/blank.jpg" width="100%">
			</div>
		</div>
		<form action="<%=request.getContextPath()%>/EncherirServlet" method="post" class="col-6">
			<div class="row mt-3 mb-4">
				<div class="col-6 fs-4">
					<c:out value="${articleVendu.nomArticle}"></c:out>
				</div>				
			</div>
			<div class="row mb-4">
				<div class="col-4">
					Description : 
				</div>
				<div class="col-8">
					<c:out value="${articleVendu.description}"></c:out>
				</div>
			</div>
			<div class="row mb-4">
				<div class="col-4">
					Catégorie : 
				</div>
				<div class="col-8">	
					<c:forEach var="id" items="${listeCategories}">
						<c:if test="${articleVendu.categorieArticle eq id.noCategorie}">
         					<c:out value="${id.libelle}"/>
         				</c:if>
      				</c:forEach>
				</div>
			</div>
			<div class="row mb-4">
				<div class="col-4">
					Meilleur offre : 
				</div>
				<div class="col-8">
					<c:choose>
						<c:when test="${enchere != null}">
	         				<c:out value="${enchere.montantEnchere} points par "></c:out>
	         				<c:forEach var="liste" items="${listeUtilisateurs}">
								<c:if test="${enchere.noUtilisateur eq liste.noUtilisateur}">
         							<c:out value="${liste.pseudo}"/>
         						</c:if>
      						</c:forEach>
	         			</c:when>
	         			<c:otherwise>
							Pas encore d'enchère !
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="row mb-4">
				<div class="col-4">
					Mise à prix : 
				</div>
				<div class="col-8">
					<c:out value="${articleVendu.miseAPrix}"></c:out> points
				</div>
			</div>
			<div class="row mb-4">
				<div class="col-4">
					Fin de l'enchère : 
				</div>
				<div class="col-8">
					<c:out value="${articleVendu.dateFinEncheres}"></c:out>
				</div>
			</div>
			<div class="row mb-4">
				<div class="col-4">
					Retrait :
				</div>
				<div class="col-8">
					<c:out value="${articleVendu.lieuRetrait.rue}"></c:out><br>
					<c:out value="${articleVendu.lieuRetrait.codePostal} ${articleVendu.lieuRetrait.ville}"></c:out>
				</div>
			</div>
			<div class="row  mt-4 mb-4">
				<div class="col-4">
					Vendeur : 
				</div>
				<div class="col-8">
					<c:out value="${articleVendu.vendeur.pseudo}"></c:out>
				</div>
			</div>
			<div class="row mt-3 mb-3">
				<div class="col-4">
					Ma proposition : 
				</div>
				<c:choose>
						<c:when test="${enchere != null}">
							<div class="col-3"><input class="form-control" name="enchere" type="number" value="${enchere.montantEnchere+1}" min="${enchere.montantEnchere+1}" max="${monProfilUtilisateur.credit}" required /></div>
	         			</c:when>
	         			<c:otherwise>
							<div class="col-3"><input class="form-control" name="enchere" type="number" value="${articleVendu.miseAPrix+1}" min="${articleVendu.miseAPrix+1}" max="${monProfilUtilisateur.credit}" required /></div>
						</c:otherwise>
					</c:choose>
				<button type="submit" class="btn btn-primary col-3" name ="submit" value="enregistrer">Enchérir</button>
			</div>
		</form>
	</div>
</body>
</html>