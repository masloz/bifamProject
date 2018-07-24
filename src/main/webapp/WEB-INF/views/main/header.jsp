<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<!-- JQUERY 3.3.1 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	
	<!-- bootstrap -->
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
		integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
		crossorigin="anonymous">
	<style>
		html, body{
			height: 100%;
		}
		.wrap{
			min-height: 75%;
		}
		body{
			background-color: #222;
			color : white;
		}
		a:hover{
			text-decoration:none;
			color : #f0f0f0;
		}
		a{
			color:white;
		}
	</style>
	<title>BIFAM</title>
</head>
<body>
	<h2>
		<a href="home.bf"> <img
			src="${ pageContext.request.contextPath }/resources/img/BIFAM-logo_white.png"
			style="">
		</a>
	</h2>
	<%-- 로그인
	 <c:if test="${ empty sessionScope.member }">
		<form action="login.bf" method="post">
			<div class="form-group">
				<label for="member_id">ID:</label>
				<input type="text" name="member_id" placeholder="Enter id" class="form-control">
			</div>
			<div class="form-group">
				<label for="member_pwd">PASSWORD :</label>
				<input type="text" name="member_pwd" placeholder="Enter password" class="form-control">
			</div>
			<button type="submit" class="btn btn-default">LOGIN</button>
		</form>
	</c:if>
	<c:if test="${not empty sessionScope.member }">
	${ sessionScope.member.member_name }님, 환영합니다.<br>
		<a href="logout.bf">LOGOUT</a>
	</c:if> --%>
	<div style="background-color: #111;">
		<div class="container">
			<nav class="navbar nav-pills justify-content-end">
				<ul class="nav">
					<li class="nav-item"><a href="home.bf" class="nav-link">Home</a></li>
					<li class="nav-item"><a href="boardList.bf" class="nav-link">Board</a></li>
					<li class="nav-item"><a href="crawling.bf" class="nav-link">Now News</a></li>
					<li class="nav-item"><a href="trackingInquiry.bf" class="nav-link">Tracking</a></li>
					<li class="nav-item"><a href="contact.bf" class="nav-link">Contact</a></li>
				</ul>
			</nav>
			<!-- <br><a href="memberSignUp.bf">SIGN UP</a> -->
		</div>
	</div>
	<br>
	
	
</body>
</html>