<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>noticeUpdateForm</title>
</head>
<body>
		<sec:authorize access="hasRole('ROLE_MEMBER')">
		<form method="post"
		 action="${pageContext.request.contextPath}/noticeUpdate.do">
		<sec:csrfInput />
		<table>
			<tr>
				<td>번호</td>	<td>${requestScope.noticeUpdateForm.noticeNo}</td>
				<td>제목</td>	<td><input type="text" name="noticeTitle" value="${requestScope.noticeUpdateForm.noticeTitle}" required="required"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea cols="90" rows="15" name="noticeContents" required="required">${requestScope.noticeUpdateForm.noticeContents}</textarea></td>
			</tr>
			<tr>
				<td>작성자</td><td>${requestScope.noticeUpdateForm.memberVO.id}</td>
				<td>작성시간</td><td>${requestScope.noticeUpdateForm.noticePostedTime}</td>
				<td>조회수</td><td>${requestScope.noticeUpdateForm.noticeHits}</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" name="noticeNo" value="${requestScope.noticeUpdateForm.noticeNo}">
					<button type="submit">수정</button>
					<button type="reset">취소</button>
				</td>
			</tr>
		</table>
		</form>
		</sec:authorize>
</body>
</html>