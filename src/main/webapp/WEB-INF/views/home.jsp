<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CRUD JPA</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		<style type="text/css">
			html{
				margin-top: 50px;
			}
		</style>		
	</head>
<body class="container">
	<div class="jumbotron">
		<h1>CRUD JPA</h1>
	</div>
	<hr/>
	
	<ul>
		<li>
			<a href="${ pageContext.request.contextPath }/pessoa/list">Cadastro de Pessoa</a>
		</li>
		<li>
			<a href="${ pageContext.request.contextPath }/cliente/list">Cadastro de Cliente</a>
		</li>
	</ul>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
</body>
</html>
