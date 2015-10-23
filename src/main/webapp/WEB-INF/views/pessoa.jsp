<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cadastro de Pessoa</title>
	</head>
<body>

	<h1>Cadastro de Pessoa</h1>
	<hr/>
	
	<c:if test="${ list != null }">
	
		<table>
			<thead>
				<tr>
					<th>ID</th><th>Nome</th><th>...</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${ list }" >
					<tr>
						<td>${ p.id }</td>
						<td>${ p.nome }</td>
						<td>
							<a href="${ pageContext.request.contextPath }/pessoa/${ p.id }">Edit</a>
							<a href="${ pageContext.request.contextPath }/pessoa/remove/${ p.id }">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</c:if>
	
	<c:if test="${ list == null }">
		<form action="${ pageContext.request.contextPath }/pessoa" method="post">
			<label>ID</label><br/>
			<input type="text" name="id" value="${ pessoa.id }" readonly="readonly" /><br/>

			<label>Nome</label><br/>
			<input type="text" name="nome" value="${ pessoa.nome }" /><br/>

			<button type="submit">Salvar</button>
			<a href="${ pageContext.request.contextPath }/pessoa/list">Cancelar</a>
			
		</form>
	</c:if>

</body>
</html>