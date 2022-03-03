<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<title>Connexion</title>
</head>
<body class="container-fluid col-10">

	<section class="row justify-content-center">
		<form class="mt-5 col-4" action="<%=request.getContextPath()%>/ConnexionServlet" method="post">
			<div class="row mb-3">
				<label class="col-4 col-form-label">Identifiant : </label>
				<div class="col-8"><input class="form-control" name="login" type="text" required /></div>
			</div>
			<div class="row mb-3 ">
				<label class="col-4 col-form-label">Mot de passe : </label>
				<div class="col-8"><input class="form-control" name="password" type="password" required /></div>
			</div>
			<div class="row mt-3 mb-5 align-items-center">
				<div class="col-6">
					<div class="d-grid gap-2"><button type="submit" class="btn btn-primary" name ="submit" value="connexion">Connexion</button></div>
				</div>
				<div class="col-6" align="right">
					<div>
						<input class="form-check-input" type="checkbox" name="remember" />
						<label class="form-check-label"> Se souvenir de moi</label>
					</div>
					<div>
						<a href="#">Mot de passe oublié</a>
					</div>
				</div>
			</div>
		</form>
	</section>
	<section class="row justify-content-center">
		<form class="col-4" action="<%=request.getContextPath()%>/InscriptionServlet" method="post">
			<div class="row mb-3">
				<div class="d-grid gap-2">
				<button type="submit" class="btn btn-primary" name ="submit" value="creeruncompte">Créer un compte</button></div> 
			</div>
		</form>
	</section>
</body>
</html>