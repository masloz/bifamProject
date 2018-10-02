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
			<h2>BOARD DETAIL VIEW</h2>
			<p>${ board.boardCategoryJoin.board_category_name } (${ board.board_category_no })</p>
			<hr color="white">
			
			<br>
					<%-- ${ board.board_no } --%>
			<div class="card bg-dark shadow rounded">
				<div class="card-header">
					<div class="row align-items-end">
						<div class="col-sm-3">
						${ board.member_id }
					</div>
					<div class="col-sm-6 text-center">
						<h4 class="card-title">${ board.board_title }</h4>
					</div>
					<div class="col-sm-3 text-right">
						${ board.board_date }
					</div>
					</div>
				</div>
				<br>
				<div class="card-body">
					${ board.board_content }
				</div>
				<br>
				<br>
				
				<c:if test="${ board.member_id eq sessionScope.member.member_id }">
					<a href="boardUpdateView.bf?board_no=${ board.board_no }">수정</a><br>
					<a href="boardDelete.bf?board_no=${ board.board_no }">삭제</a>
				</c:if>
			</div>	
		</div>
	</div>
	<c:import url="../main/footer.jsp"/>
</body>
</html>