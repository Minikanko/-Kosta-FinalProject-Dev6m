<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
<script type="text/javascript">
alert("잘못된 진입경로 입니다.");
location.href="${pageContext.request.contextPath}/home.do";
</script>
</body>
</html>