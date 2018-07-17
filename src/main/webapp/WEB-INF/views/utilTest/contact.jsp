<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Contact</title>
</head>
<body>
	<c:import url="../main/header.jsp"/>
	
	<h1>CONTACT</h1>
	<form action="sendMail.bf">
		<input type="text" placeholder="name" name="name"><br>
		<input type="text" placeholder="e-mail" name="email"><br>
		<textarea placeholder="comment" name="comment"></textarea><br>
		<input type="submit" value="Send">
	</form>
	
</body>
</html>