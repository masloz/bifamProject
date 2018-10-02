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
			<h1>EXCEL IO</h1>
			<hr color="white">
			<form action="refinement.bf" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<input type="file" name="file" class="form-control form-control-sm">
					<input type="hidden" name="path" id="path">
				</div>
				<div class="text-right">
					<input type="submit" value="Refinement" class="btn btn-dark" id="subBtn">
				</div>
			</form>
			
		</div>
	</div>
	<c:import url="../main/footer.jsp"/>
</body>
<script>
$('[name="file"]').change(function (){
	$('#path').attr("value", $('[name="file"]').val());
	var str = $('[name="file"]').val();
	var xlsx = "xlsx";
	if(str.substring(str.length - 4, str.length) != xlsx){
		$('#subBtn').attr("disabled", true);
		alert("xlsx파일만 가능합니다.");
	}
});
</script>

</html>