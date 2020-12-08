<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#commentsWriteButton").click(function(){
			alert('1');
			window.opener.name = "parentPage";
			document.commentsWriteForm.target = "parentPage";
			document.commentsWriteForm.action = "${pageContext.request.contextPath}/commentsWrite.do";
			document.commentsWriteForm.submit();
			self.close();
		})
		$("#star_point a").click(function(){
			$(this).parent().children("a").removeClass("on");
			$(this).addClass("on").prevAll("a").addClass("on");
			return false;
		})
	})
</script>
<style>
	#star_point a{
		text-decoration: none;
		color: gray;
	}
	#star_point a.on{
		color: red;
	}
</style>
<title>commentsWriteForm</title>
</head>
<body>
	<form name="commentsWriteForm" method="post">
	<sec:csrfInput/>
	<table>
		<tr>			
			<td>별점</td>
			<td><input type="number" id="commentsStars"	name="commentsStars"
			min="1" max="10" required="required"></td>
			<td>
				
			</td>
		</tr>
		<tr>
			<td>한줄평</td><td><textarea cols="40" rows="15" id="commentsContents"
			name="comments" placeholder="한줄평을 입력해주세요!" required="required"></textarea></td>
		</tr>
		<tr>
			<td>
				<input type="hidden" name="contentsNo" value="${requestScope.contentsNo}">
				<input type="button" id="commentsWriteButton" value="작성">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>