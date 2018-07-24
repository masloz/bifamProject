<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<c:import url="../main/header.jsp"/>
</head>
<body>
	<div class="wrap">
		<div class="container">
			<h1>CONTACT</h1>
			<hr color="white">
			<form action="sendMail.bf">
				<div class="row">
					<div class="form-group col-sm-6">
						<label>Name :</label>
						<input type="text" placeholder="name" name="name" class="form-control form-control-sm">
					</div>
					<div class="form-group col-sm-6">
						<label>E-mail :</label>
						<input type="email" placeholder="e-mail" name="email" class="form-control form-control-sm">
					</div>
				</div>
				<div class="form-group">
					<label>Comment :</label>
					<textarea placeholder="comment" name="comment" class="form-control"></textarea>
				</div>
				<div class="text-right">
					<input type="submit" value="Send" class="btn btn-dark">
				</div>
				
			</form>
		</div>
	</div>
	<c:import url="../main/footer.jsp"/>
</body>
</html>