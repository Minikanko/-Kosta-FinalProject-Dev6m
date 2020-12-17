<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
#partyWriteForm{
background-color: #99ccff;
height: 75%;
width: 75%;
}
</style>
<script type="text/javascript">
$(document).ready(function () {
	$('#membershipType').change(function () {
		var selectType=$(this).val();
		 $.ajax({
			type : "get",
			url : "${pageContext.request.contextPath}/select1.do",
			dataType : "json",
			data : "no="+ selectType,
			success : function(result) {
				$("#partyHeadCount").html("");
				$('#partyHeadCount').append('<option value="">모집할 인원을 선택해주세요</option>');	
			var concurrentUsers = result.concurrentUsers;
			for(var i = 1 ; i<=concurrentUsers ; i++){
				$('#partyHeadCount').append("<option value="+i+">"+i+"명</option>");	
			}
		 }
		});//ajax 
	})//첫번째 select 박스
	$('#partyHeadCount').change(function () {
		
	})// 두번째 select 박스
})

</script>

<div class="tableMargin" id="commentsList">
<div class="container-lg boardClassMain">
<form action="${pageContext.request.contextPath}/partywrite.do" method="post">
<sec:csrfInput/><!-- csrf 토큰 -->
<br><br><br><br><br><br><br><br><br><br><br>
<table border="1">
 <caption>파티 글 쓰는곳</caption>
<tr>
<td>이용권 종류를 선택해주세요:</td>
</tr>
</table>

<!-- 이용권 셀렉박스 -->
<select name="membershipVO.membershipNo" id="membershipType" required >
<option value="">이용권 종류를 선택해주세요</option>
<option value="1">BASIC</option>
<option value="2">STANDARD</option>
<option value="3">PREMIUM</option>
</select>

<!-- 모집인원 셀렉박스 -->
<select name="partyHeadCount" id="partyHeadCount" required>
<option>모집할 인원을 선택해주세요</option>
</select>
<br>
제목:<input type="text" name=partyTitle required="required">
<input type="submit" value="등록">
</form>
</div>
</div>
