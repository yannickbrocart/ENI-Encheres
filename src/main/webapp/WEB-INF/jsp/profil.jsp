<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<title>Profil utilisateur</title>
</head>
<body class="container-fluid col-10">

	<section class="row justify-content-center">
		<div class="row mt-5 justify-content-center">
			<div class="col-2"><c:out value="Pseudo : "/></div>
			<div class="col-5 h4"><c:out value="${utilisateur.pseudo}" /></div>
		</div>
		<div class="row mt-3 justify-content-center">
			<div class="col-2"><c:out value="Nom : "/></div>
			<div class="col-5"><c:out value="${utilisateur.nom}" /></div>
			</div>
		<div class="row mt-3 justify-content-center">
			<div class="col-2"><c:out value="Prénom : "/></div>
			<div class="col-5"><c:out value="${utilisateur.prenom}" /></div>
			</div>
		<div class="row mt-3 justify-content-center">
			<div class="col-2"><c:out value="Email : "/></div>
			<div class="col-5"><c:out value="${utilisateur.email}" /></div>
			</div>
		<div class="row mt-3 justify-content-center">
			<div class="col-2 "><c:out value="Téléphone : "/></div>
			<div class="col-5"><c:out value="${utilisateur.telephone}" /></div>
			</div>
		<div class="row mt-3 justify-content-center">
			<div class="col-2"><c:out value="Rue : "/></div>
			<div class="col-5"><c:out value="${utilisateur.rue}" /></div>
			</div>
		<div class="row mt-3 justify-content-center">
			<div class="col-2"><c:out value="Code postal : "/></div>
			<div class="col-5"><c:out value="${utilisateur.codePostal}" /></div>
			</div>
		<div class="row mt-3 justify-content-center">
			<div class="col-2"><c:out value="Ville : "/></div>
			<div class="col-5"><c:out value="${utilisateur.ville}" /></div>
		</div>
		<div class="row mt-5 mb-3 justify-content-center gap-5">
			<a class="col-3" href="<%=request.getContextPath()%>/ModifierMonProfilServlet">
			<button type="button" class="btn btn-primary col-12">Modifier</button></a>
		</div>
	</section>
</body>
</html>