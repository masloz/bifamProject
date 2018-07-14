<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<c:import url="../main/header.jsp"/>
	<h2>BOARD DETAIL VIEW</h2>
	
	게시판 번호 : ${ board.board_no }<br>
	게시판 카테고리 : ${ board.boardCategoryJoin.board_category_name } (${ board.board_category_no })<br>
	제목 : ${ board.board_title }<br>
	내용 : ${ board.board_content }<br>
	등록일 : ${ board.board_date }<br>
	아이디 : ${ board.member_id }<br>
	
	<c:if test="${ board.member_id eq sessionScope.member.member_id }">
		<a href="boardUpdateView.bf?board_no=${ board.board_no }">수정</a><br>
		<a href="boardDelete.bf?board_no=${ board.board_no }">삭제</a>
	</c:if>

</body>
</html>