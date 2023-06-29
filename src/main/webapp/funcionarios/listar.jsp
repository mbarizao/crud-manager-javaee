<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
<meta charset="UTF-8">
<title>Listar Funcionário</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<style><%@include file="../css/index.css"%></style>
</head>
<body>

<!-- Barra de navegação -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<a class="navbar-brand" href="/manager">Controle de funcionários</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse justify-content-end"
		id="navbarNav">
		<div class="dropdown">
			<button class="btn btn-light dropdown-toggle" type="button"
				data-bs-toggle="dropdown" aria-expanded="false">${user.nome}</button>
			<ul class="dropdown-menu">
				<li><a class="dropdown-item" href="ServletAuth?action=logout">Logout</a></li>
			</ul>
		</div>
	</div>
</nav>


<section class="container p-5">
	  <h2>Funcionarios</h2>
	  <h3>Listagem de funcionarios</h3>
	  <div class="help-block text-center mt-3">
	  	<p>${alert}</p>
	  </div>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Profissão</th>
            <th>Setor</th>
            <th class="col-md-2">Ações</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="funcionario" items="${funcionarios}">
            <tr>
              <td>${funcionario.id}</td>
              <td>${funcionario.nome}</td>
              <td>${funcionario.profissao}</td>
              <td>${funcionario.setor.getDescricao()}</td>
              <td>
	            <a href="/manager/ServletFuncionario?action=edit&id=${funcionario.id}" class="btn btn-primary"><i class="fas fa-edit"></i></a>
	            <a href="/manager/ServletFuncionario?action=delete&id=${funcionario.id}" class="btn btn-danger"><i class="fas fa-trash"></i></a>
	          </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
</section>

<!-- Inclua os arquivos de script do Bootstrap e do Font Awesome para os ícones -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/6aaf2c5999.js"></script>

</body>
</html>

