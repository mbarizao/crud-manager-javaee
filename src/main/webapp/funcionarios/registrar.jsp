<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
<meta charset="UTF-8">
<title>Registrar funcionário</title>
<!-- Adicione os arquivos CSS do Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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

<%

    String editId = request.getParameter("id");
	// Verifica se está em modo edição e cria uma variável de controle para isso
 	boolean editMode = request.getParameter("action") != null && request.getParameter("action").equals("edit");

	String editIdQueryParam = "";
	
	if (editMode) {
	  editIdQueryParam = "?editId=" + editId;
	}
%>

<section class="container mt-5 form-container">
  <h3>Registro de funcionarios</h3>
  <form action="/manager/ServletFuncionario<%= editIdQueryParam %>" method="post">
     <div class="form-group">
            <label for="nome">Nome:</label>
            <input type="text" name="nome" id="nome" class="form-control" value="${nome}" required>
        </div>

        <div class="form-group">
            <label for="profissao">Profissão:</label>
            <input type="text" name="profissao" id="profissao" class="form-control" value="${profissao}" required>
        </div>

        <div class="form-group">
            <label for="idSetor">Setor:</label>
            <select name="idSetor" id="idSetor" class="form-control" required>
                <c:forEach var="setor" items="${setores}">
                    <option value="${setor.id}" ${setor.id eq idSetor ? "selected" : ""}>${setor.descricao}</option>
                </c:forEach>
            </select>
        </div>

    <% if (editMode) { %>
    	<button type="submit" class="btn btn-warning btn-block">Atualizar</button>
	<% } else { %>
	    <button type="submit" class="btn btn-primary btn-block">Registrar</button>
	<% } %>

  </form>
  <div class="help-block mt-3">
  	<p>${success}</p>
  </div>
</section>

<!-- Incluir os arquivos de script do Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>
