<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<c:import url="../main/header.jsp" />
</head>
<body>
	<div class="wrap">
		<div class="container">
			<h1>BOARD</h1>
			<br>
	
			<table class="table table-hover shadow rounded">
				<thead>
					<tr>
						<th>NO</th>
						<th>Title</th>
						<th>Content</th>
						<th>Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${ boardList }">
						<tr>
							<td>${ board.board_no }</td>
							<td><a href="boardDetail.bf?board_no=${ board.board_no }">${ board.board_title }</a></td>
							<td>${ board.member_id }</td>
							<td>${ board.board_date }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="text-right">
				<a href="boardWriteForm.bf" class="btn btn-dark">글쓰기</a>
			</div>
			<%-- <c:if test="${ empty session.member }">
				
			</c:if> --%>
		</div>
	</div>
	<c:import url="../main/footer.jsp" />
</body>
</html>