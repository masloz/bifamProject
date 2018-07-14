<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Tracking</title>
</head>
<body>
	<c:import url="../../main/header.jsp"/>
	
	<!-- Tracking Inquiry -->
	<h1>TRACKING</h1>
	<hr>
	<h2>택배 조회</h2>
	
	<select name="code">
	<c:forEach var="companyList" items="${ companyList }">
		<option value="${ companyList.code }">${ companyList.name }</option>
	</c:forEach>
	</select><br>
	<input type="text" name="invoice" placeholder="송장번호 입력"><br>
	<input type="button" id="inquiryButton" value="조회하기" onclick="trackingInquiry()">
	
	<br>
	<hr>
	<br>
	
	<div id="trackingInfo">
		
	</div>
	
	<!-- Tracking Info -->
	<script>
	// 택배 조회
	function trackingInquiry(){
		$.ajax({
			url : "trackingInfo.bf",
			type : "get",
			dataType : "json",
			data : {"code" : $('[name="code"]').val(),
					"invoice" : $('[name="invoice"]').val()},
			success : function(tracking){
				var trackArray = tracking.trackingDetails;
				var html = "<table><tr><th>운송장 번호</th><th>보낸 사람</th><th>받는 사람</th><th>수령 주소</th><th>상품정보</th><th>배송상태</th></tr>";
				
				var level = tracking.level;
				switch(level){
					case 1:	level = "배송준비";	break;
					case 2:	level = "집화완료";	break;
					case 3:	level = "배송진행 중";	break;
					case 4:	level = "지점 도착";	break;
					case 5:	level = "배송출발";	break;
					case 6:	level = "배송완료";	break;
				}
				html += "<tr><td>" + tracking.invoiceNo + "</td>";
				html += "<td>" + tracking.senderName + "</td>";
				html += "<td>" + tracking.receiverName + "</td>";
				html += "<td>" + tracking.receiverAddr + "</td>";
				html += "<td>" + tracking.itemName + "</td>";
				html += "<td>" + level + "</td></tr></table><br>";
				
				html += "<table><tr><th>시간</th><th>위치</th><th>배송상태</th><th>전화번호</th><th>전화번호2</th></tr>";
				for(var i in trackArray){
					
					html += "<tr><td>" + trackArray[i].timeString + "</td>";
					html += "<td>" + trackArray[i].where + "</td>";
					html += "<td>" + trackArray[i].kind + "</td>";
					html += "<td>" + trackArray[i].telno + "</td>";
					html += "<td>" + trackArray[i].telno2 + "</td></tr>";
					
				}
				
				$('#trackingInfo').html(html + "</table>");
				
			}, error : function(){
				alert("송장번호랑 저거 뭐냐 코드 다시 확인 해라.");
			}
		});
		// 과부화 방지 (Prevent Multiple clicks)
		$('#inquiryButton').attr("disabled", true);
	}
	
	<!-- 송장번호 수정 시 버튼 활성화 (when keyup, button activate) -->
	$('[name="invoice"]').keyup(function (){
		$('#inquiryButton').attr("disabled", false);
	});
	$('[name="code"]').change(function (){
		$('#inquiryButton').attr("disabled", false);
	});
	
	</script>
	
</body>
</html>