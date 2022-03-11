<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/encheres.css" rel="stylesheet">
<title>Vendre un article</title>
</head>
<body class="container-fluid col-10">
	<header class="row mt-3 align-items-center">
		<div id="logo" class="col-6 fs-2">
			<a href="<%=request.getContextPath()%>/">ENI-Ench�res</a>
		</div>
	</header>
	<div class="row justify-content-center">
		<h3 class="mt-5 text-center">Nouvelle vente</h3>
	</div>
	<div class="row">
		<div class="col-4">
			<div class="mt-3">
				<img alt="Photo article" src="images/blank.jpg" width="100%">
			</div>
		</div>
		<form action="<%=request.getContextPath()%>/VendreUnArticleServlet" method="post" class="col-6">
			<div class="row mt-3 mb-4">
				<label class="col-3 col-form-label">Article : </label>
				<div class="col-9"><input class="form-control" name="article" type="text" required /></div>
			</div>
			<div class="row mb-4">
				<label class="col-3 col-form-label">Description : </label>
				<div class="col-9"><textarea class="form-control" name="description" rows="3" required></textarea></div>
			</div>
			<div class="row mb-4">
				<label class="col-6 col-form-label">Cat�gorie : </label>
				<div class="col-6">
					<select class="form-select" name="categorie" required>
						<option selected>Choisir...</option>
							<c:forEach var="id" items="${listeCategories}">
							<option value="${id.noCategorie}">${id.libelle}</option>
							</c:forEach>
 					 </select>
				</div>
			</div>
			<div class="row mb-4">
				<label class="col-3 col-form-label">Photo de l'article : </label>
				<div class="d-grid gap-2 col-9"><input type="file" class="form-control" accept="image/png, image/jpeg" name ="Uploader"></div>
			</div>
			<div class="row mb-4">
				<label class="col-8 col-form-label">Mise � prix : </label>
				<div class="col-4"><input class="form-control" name="miseAPrix" type="number" value="1" required /></div>
			</div>
			<div class="row mb-4">
				<label class="col-6 col-form-label">D�but de l'ench�re : </label>
				<div class="col-6"><input class="form-control" name="dateDebutEncheres" type="date" value="${now}" required /></div>
			</div>
			<div class="row mb-4">
				<label class="col-6 col-form-label">Fin de l'ench�re : </label>
				<div class="col-6"><input class="form-control" name="dateFinEncheres" type="date" required /></div>
			</div>
			<fieldset class="border">
                <legend>Retrait</legend>
				<div class="row mb-4">
					<label class="col-3 col-form-label">Rue : </label>
					<div class="col-9"><input class="form-control" name="rue" type="text" value="${sessionScope.monProfilUtilisateur.rue}" required /></div>
				</div>
				<div class="row mb-4">
					<label class="col-3 col-form-label">Code postal : </label>
					<div class="col-9"><input class="form-control" name="codePostal" type="text" value="${sessionScope.monProfilUtilisateur.codePostal}" required /></div>
				</div>
				<div class="row mb-4">
					<label class="col-3 col-form-label">Ville : </label>
					<div class="col-9"><input class="form-control" name="ville" type="text" value="${sessionScope.monProfilUtilisateur.ville}" required /></div>
				</div>
			</fieldset>	
			<div class="row mt-3 mb-3 gap-5" style="padding-left:14px;">
					<button type="submit" class="btn btn-primary col-4" name ="submit" value="enregistrer">Enregistrer</button>
					<a class="col-4" href="<%=request.getContextPath()%>/">
					<button type="button" class="btn btn-primary col-12">Annuler</button></a>
			</div>
		</form>
	</div>
	
</body>
</html>