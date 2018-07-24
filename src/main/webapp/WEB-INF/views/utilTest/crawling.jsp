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
			<h1>CRAWLING</h1>
			<hr color="white">
			<!-- NAVER NEWS -->
			<h2>이 시각 주요 뉴스</h2><h5>(Naver news)</h5><br>
			
			<div>
			<c:forEach var="crawling" items="${ naverCrawlingList }">
				<h5><a href="${ crawling.link }" target="_blank">${ crawling.title }</a></h5>
			</c:forEach>
			</div>
			<br>
			
			<!-- DAUM IT -->
			<h2>DAUM IT NEWS</h2><h5>(Daum news)</h5><br>
			<div>
			<c:forEach var="crawling" items="${ daumCrawlingList }">
				<h5><a href="${ crawling.link }" target="_blank">${ crawling.title }</a></h5>
			</c:forEach>
			</div>
		</div>
	</div>
	<c:import url="../main/footer.jsp"/>
</body>
</html>