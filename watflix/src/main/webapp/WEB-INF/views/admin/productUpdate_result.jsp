<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
alert("${productName} 상품이 수정됐습니다.")
location.href="${pageContext.request.contextPath}/productList.do";
</script>
</body>
</html>