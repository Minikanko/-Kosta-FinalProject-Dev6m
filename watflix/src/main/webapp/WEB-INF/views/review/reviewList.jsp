<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- reviewList페이지 DIV -->
<div class="tableTopMargin" id="reviewList">
<div class="container-lg">
  <h3><p align="center">리뷰리스트</p></h3>            
  <table class="table table-hover">
    <thead>
      <tr>
		<th class="reviewTitle">제목</th>
		<th class="reviewWriter">작성자</th>
		<th class="reviewDate">작성일</th>
		<th class="reviewLikes">추천</th>
		<th class="reviewHits">조회</th>
      </tr>
    </thead>
   <tbody>
		<c:forEach var="rvo" items="${requestScope.lvo.reviewList}">
			<tr style=" border-bottom: 1px solid #ddd;">
				<!-- 리뷰제목 불러오기 -->
				<td><a href="${pageContext.request.contextPath}/reviewDetail.do?reviewNo=${rvo.reviewNo}">${rvo.reviewTitle}</a></td>
				<td>${rvo.memberVO.id}</td>					<!-- 리뷰작성자 불러오기 -->
				<td>${rvo.reviewPostedTime}</td>			<!-- 리뷰작성일 불러오기 -->
				<td>${rvo.reviewLikes}</td>					<!-- 리뷰추천수 불러오기 -->
				<td >${rvo.reviewHits}</td>					<!-- 리뷰조회수 불러오기 -->
			</tr>	
		</c:forEach>
	</tbody>
  </table>
</div>
<!-- 페이징과 리뷰작성을 감싸는 DIV -->
<div>
<!-- 페이징 DIV -->
<div class="pagingInfo" style="position:center;">
	<c:set var="rpb" value="${requestScope.lvo.pagingBean}"/>
	<ul class="pagination">
		<c:if test="${rpb.previousPageGroup}">		<!-- 이전 페이지으로 넘어갈 만큼 충분한 게시글이있으면 '<<' 페이지 넘기기 버튼이 생성된다. -->
		<li><a href="${pageContext.request.contextPath}/reviewList.do?pageNo=${rpb.startPageOfPageGroup-1}">&laquo;</a></li>	<!-- $laquo는 페이징에 나타나는'<<'왼쪽페이지 버튼이다. -->
		</c:if>
		<c:forEach var="i" begin="${rpb.startPageOfPageGroup}" end="${rpb.endPageOfPageGroup}">
			<c:choose>
				<c:when test="${rpb.nowPage != i}">
					<li><a href="${pageContext.request.contextPath}/reviewList.do?pageNo=${i}">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li class="active"><a href="#">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${rpb.nextPageGroup}">			<!-- 다음 페이지로 넘어갈 만큼 충분한 게시글이 있으면 '>>' 페이지 넘기기 버튼이 생성된다. -->
			<li><a href="${pageContext.request.contextPath}/reviewList.do?pageNo=${rpb.endPageOfPageGroup+1}">&raquo;</a></li>
		</c:if>
	</ul>
</div><!-- 페이징 DIV 끝 -->
<!-- 리뷰작성 DIV -->
<div align="right">
<button type="button" class="btn btn-default" style="width: 126px;" onclick="location.href = '${pageContext.request.contextPath}/reviewWriteForm.do?contentsNo=${requestScope.lvo.reviewList[0].contentsVO.contentsNo}' ">리뷰작성하기</button>
</div><!-- 리뷰작성 DIV 끝 -->
</div><!-- 페이징과 리뷰작성을 감싸는 DIV 끝 -->
</div><!-- reviewList페이지 DIV 끝-->
