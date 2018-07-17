<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Bicycle Family</title>
</head>
<body>
<c:import url="main/header.jsp"/>

<c:if test="${ empty sessionScope.member }">
	<form action="login.bf" method="post">
		ID : <input type="text" name="member_id"><br>
		PASSWORD : <input type="password" name="member_pwd"><br>
		<input type="submit" value="LOGIN">
	</form>
</c:if>
<c:if test="${not empty sessionScope.member }">
	${ sessionScope.member.member_name }님, 환영합니다.<br>
	<a href="logout.bf">LOGOUT</a>
</c:if>

<br><a href="memberSignUp.bf">SIGN UP</a>
<br><a href="boardList.bf">게시판 이동</a>
<br><a href="crawling.bf">Now News</a>
<br><a href="trackingInquiry.bf">Tracking</a>
<br><a href="contact.bf">Contact</a>


<%-- 네아로

<%
    String clientId = "2VqQsYhBIuet4sBOze_e";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://228220.ddns.net:228/bifam/", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>
  <a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a> --%>
</body>
</html>
