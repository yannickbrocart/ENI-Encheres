<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/encheres.css" rel="stylesheet">
<title>Enchère non commencée côté vendeur</title>
</head>
<body class="container-fluid col-10">
	<header class="row mt-3 align-items-center">
		<div id="logo" class="col-6 fs-2">
			<a href="<%=request.getContextPath()%>/">ENI-Enchères</a>
		</div>
	</header>
	<div class="row justify-content-center">
		<h3 class="mt-5 text-center">Vente à venir</h3>
	</div>
	<div class="row">
		<div class="col-4">
			<div class="mt-3">
				<img alt="Photo article" src="images/blank.jpg" width="100%">
			</div>
		</div>
		<section class="col-6">
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
					Mise à prix : 
				</div>
				<div class="col-8">
					<c:out value="${articleVendu.miseAPrix}"></c:out> points
				</div>
			</div>
			<div class="row mb-4">
				<div class="col-4">
					Début de l'enchère : 
				</div>
				<div class="col-8">
					<c:out value="${articleVendu.dateDebutEncheres}"></c:out>
				</div>
			</div><div class="row mb-4">
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
		</section>
	</div>
	
</body>
</html>