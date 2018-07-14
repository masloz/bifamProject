<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>News Crawling Test</title>
</head>
<body>
	<c:import url="../main/header.jsp"/>
	
	<h1>CRAWLING</h1>
	<hr>
	<!-- NAVER NEWS -->
	<h2>이 시각 주요 뉴스</h2><h5>(Naver news)</h5><br>
	
	<c:forEach var="crawling" items="${ naverCrawlingList }">
		<a href="${ crawling.link }" target="_blank">${ crawling.title }</a><br>
	</c:forEach>
	
	<br>
	
	<!-- DAUM IT -->
	<h2>DAUM IT NEWS</h2><h5>(Daum news)</h5><br>
	
	<c:forEach var="crawling" items="${ daumCrawlingList }">
		<a href="${ crawling.link }" target="_blank">${ crawling.title }</a><br>
	</c:forEach>
	
</body>
</html>