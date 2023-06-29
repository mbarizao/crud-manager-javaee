<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
  <title>Sistema de Funcionários - Login</title>
  <!-- Inclua os arquivos CSS do Bootstrap -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <style><%@include file="css/index.css"%></style>
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="col-md-4 col-md-offset-4">
        <div class="login-container">
          <div class="login-logo">
            <h1>Controle de funcionários</h1>
          </div> 
          <form class="login-form" action="ServletAuth?action=login" method="post">
            <div class="form-group">
              <label for="email">Email:</label>
              <input type="email" class="form-control" id="email" name="email" placeholder="Digite seu email" required>
            </div>
            <div class="form-group">
              <label for="password">Senha:</label>
              <input type="password" class="form-control" id="password" name="password" placeholder="Digite sua senha" required>
            </div>
            <input type="hidden" name="acao" value="logar"> 
            <button type="submit" class="btn btn-primary">Entrar</button>
          </form>
          <div class="help-block">
            <p class="error">${erro}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
