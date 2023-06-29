<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Controle de funcionários</title>
<!-- Adicione os arquivos CSS do Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style><%@include file="css/index.css"%></style>
</head>
<body>

	<!-- Barra de navegação -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="#">Controle de funcionários</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-end"
			id="navbarNav">
			<span class="mx-3 btn btn-outline-light">Acessos:
					<%
					 	Cookie[] cookies = request.getCookies();
				    
					    if (cookies != null) {
					        for (Cookie cookie : cookies) {
					            if (cookie.getName().equals("menuQuantityAccess")) {
					                String menuQuantityAccess = cookie.getValue();
					                out.println(menuQuantityAccess);
					                break;
					            }
					        }
					     }
					%>
			</span>
			<div class="dropdown">
				<button class="btn btn-light dropdown-toggle" type="button"
					data-bs-toggle="dropdown" aria-expanded="false">${user.nome}</button>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item" href="ServletAuth?action=logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<section class="container my-4">
		<div class="row justify-content-center">
			<div class="col mb-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Setores</h5>
						<p class="card-text">Gerencie os setores da empresa</p>
						<a href="setores/registrar.jsp" class="btn btn-primary btn-block">Registrar
							setor</a> <a
							href="/manager/ServletSetor?nextPage=/setores/listar.jsp"
							class="btn btn-primary btn-block">Listar setores</a>
					</div>
				</div>
			</div>
			<div class="col mb-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Funcionários</h5>
						<p class="card-text">Gerencie os funcionários da empresa</p>
						<a
							href="/manager/ServletSetor?nextPage=funcionarios/registrar.jsp"
							class="btn btn-primary btn-block">Registrar funcionário</a> <a
							href="/manager/ServletFuncionario?nextPage=funcionarios/listar.jsp"
							class="btn btn-primary btn-block">Listar funcionários</a>
					</div>
				</div>			
			</div>
			<div class="col mb-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Usuários</h5>
						<p class="card-text">Gerencie os usuários do sistema</p>
						<a
							href="/manager/ServletSetor?nextPage=usuarios/registrar.jsp"
							class="btn btn-primary btn-block">Registrar usuário</a> <a
							href="/manager/ServletUsuario?nextPage=usuarios/listar.jsp"
							class="btn btn-primary btn-block">Listar usuários</a>
					</div>
				</div>			
			</div>
		</div>
	</section>

	<!-- Adicione os arquivos JS do Bootstrap -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
</body>
</html>
