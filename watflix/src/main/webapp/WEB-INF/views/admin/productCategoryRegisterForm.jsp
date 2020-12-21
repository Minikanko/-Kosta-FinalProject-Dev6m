<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<title>상품카테고리 등록</title>
	<!-- Bootstrap 4.4.1 -->
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/adminBootstrap.css">
	<!-- Flickity 2.2.1 -->
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/flickity.css">
	<!-- JQuery UI -->
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
	<!-- Main CSS -->
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/adminStyle.css">
	<!-- jqeury -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("#productCategoryRegisterComBtn").click(function(){
			var nameLength = $("#productCategoryName").val().length
			if(nameLength<1 || nameLength>10){
				alert("카테고리명은 1글자 이상 10글자 이하만 가능합니다.");
				$("#productCategoryName").focus();
				return false;
			}
			else{
				$.ajax({
					type: "post",
					url:"${pageContext.request.contextPath}/productCategoryNameCheck.do",
					beforeSend:function(xhr){  
			            xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
			        },
					data: "productCategoryName="+$("#productCategoryName").val(),
					success:function(result){ // result변수로 응답정보가 전달된다.
						if(result==='no'){
							$("#productCategoryRegisterForm").submit();
						}
						else{
							alert("해당 카테고리명은 이미 존재합니다. 다시 확인 바랍니다.");
							$("#productCategoryName").focus();
						}
					}
				});//ajax
			}
		})
	})
	</script>
<div class="container boardClassMain">
  <h2>상품카테고리 등록</h2>
  <form action="${pageContext.request.contextPath}/productCategoryRegister.do" id="productCategoryRegisterForm" method="post">     
	<sec:csrfInput/>
	  <table class="table table-bordered" style="border-radius: 1.5px;">
		<tr>
			<td style="width:40%;">카테고리 이름</td>
			<td class="categoryName">
				<input type="text" name="productCategoryName" id="productCategoryName">
			</td>					
		</tr>	
	  </table>
  </form>
	<div class="boardBottomDiv">
		<div class="writeBtn">
			<div>
				<button type="button" class="btn-default boardBtnSubmit" id="productCategoryRegisterComBtn">등록</button>
			</div>
		</div>
	</div>
</div>
