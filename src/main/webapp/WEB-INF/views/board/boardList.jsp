<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<c:import url="../main/header.jsp"/>
	<h2>BOARD</h2>
	
	<c:forEach var="board" items="${ boardList }">
		${ board.board_no }, 
		<a href="boardDetail.bf?board_no=${ board.board_no }">${ board.board_title }</a>, ${ board.member_id }, ${ board.board_date }<br>
	</c:forEach>
	
	<c:if test="${ empty session.member }">
		<a href="boardWriteForm.bf">글쓰기</a>
	</c:if>
</body>
</html>