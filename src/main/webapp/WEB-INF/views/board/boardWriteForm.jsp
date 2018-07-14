<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form action="boardWrite.bf" method="POST">
	<% //TODO 카테고리 처리(WRITE FORM) %>
		<input type="text" name="board_title">
		<input type="hidden" name="member_id" value="${ sessionScope.member.member_id }">
		<hr>
		<textarea name="board_content" rows="20" cols="100"></textarea>
		<button type="submit">작성하기</button>
	</form>
</body>
</html>