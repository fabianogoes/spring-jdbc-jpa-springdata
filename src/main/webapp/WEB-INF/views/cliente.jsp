<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cadastro de Cliente</title>
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
		<h1>Cadastro de Cliente</h1>
	</div>
	
	<c:if test="${ list != null }">
	
		<table class="table table-bordered table-condensed table-striped table-hover">
			<thead>
				<tr>
					<th class="text-center col-md-1">ID</th>
					<th>Nome</th>
					<th>CPF</th>
					<th class="text-center col-md-1">
						<a href="${ pageContext.request.contextPath }/cliente/" class="btn btn-primary " >
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						</a>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${ list }" >
					<tr>
						<th class="text-center col-md-1">${ p.id }</td>
						<td>
							<a href="${ pageContext.request.contextPath }/cliente/${ p.id }" >${ p.nome }</a>
						</td>
						<td>
							<a href="${ pageContext.request.contextPath }/cliente/${ p.id }" >${ p.cpf }</a>
						</td>
						<td class="text-center col-md-1">
							<a href="${ pageContext.request.contextPath }/cliente/${ p.id }" class="btn btn-warning btn-sm" >
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</a>
							<a href="javascript:showConfirmDelete(${p.id});" class="btn btn-danger btn-sm">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</c:if>
		
	<!-- Modal -->
	<div class="modal fade bs-example-modal-sm" id="modalConfirmaDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h1 class="modal-title text-center" id="myModalLabel">Exclusão</h1>
	      </div>
	      
	      <div class="modal-body text-center">
		      <h3>Confirma a exclusão do registro?</h3>
	      </div>
	      
	      <div class="modal-footer">
	      	<div class="row">
	      		
	      		<div class="col-md-1">
					<a href="#" class="btn btn-danger" id="idDeleteCliente" >
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Confirma Exclusão
					</a>
	      		</div>
	      		<div class="col-md-10"></div>
	      		<div class="col-md-1">
					<a href="${ pageContext.request.contextPath }/cliente/remove/${ p.id }" class="btn btn-default pull-right" >
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Cancelar
					</a>
				</div>
	      	</div>
	      </div>
	      
	    </div>
	  </div>
	</div>	
	
	<c:if test="${ list == null }">
		<form action="${ pageContext.request.contextPath }/cliente" method="post">
			<div class="row">
				<div class="col-md-2">
					<label>ID</label>
					<input type="text" class="form-control" name="id" value="${ cliente.id }" readonly="readonly" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-10">
					<label>Nome</label><br/>
					<input type="text" class="form-control" name="nome" value="${ cliente.nome }" /><br/>
				</div>
				<div class="col-md-2">
					<label>CPF</label><br/>
					<input type="text" class="form-control" name="cpf" value="${ cliente.cpf }" /><br/>
				</div>
			</div>

			<hr/>

			<div class="row">
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Salvar
					</button>
					<a href="${ pageContext.request.contextPath }/cliente/list" class="btn btn-default pull-right" >
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Cancelar
					</a>
				</div>
			</div>
			
		</form>
	</c:if>
	
	<c:if test="${ message != null }">
		<div class="alert alert-success alert-dismissible text-center" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>Informação!</strong><br/> 
		  ${ message }
		</div>		
	</c:if>
	
	<hr/>
	
	<div class="text-center">
		<a href="${ pageContext.request.contextPath }/" >
			Home
		</a>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function showConfirmDelete(id) { 
			$('#idDeleteCliente').attr('href', '${ pageContext.request.contextPath }/cliente/remove/'+id);
			$('#modalConfirmaDelete').modal('show');
		}	
	</script>

</body>
</html>