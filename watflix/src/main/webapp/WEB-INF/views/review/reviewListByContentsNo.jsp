<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container boardClassMain" id="reviewList">
  <h2>리뷰리스트</h2>           
  <table class="table table-hover table-bordered" style="border-radius: 1.5px;">
    <thead>
      <tr>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>추천</th>
		<th>조회</th>
      </tr>
    </thead>
    <tbody>
		<c:forEach var="rvoc" items="${requestScope.reviewListByContentsNo.reviewList}">
			<tr>
				<!-- 리뷰제목 불러오기 -->
				<td><a href="${pageContext.request.contextPath}/reviewDetail.do?reviewNo=${rvoc.reviewNo}">${rvoc.reviewTitle}</a></td>
				<td>${rvoc.memberVO.id}</td>					<!-- 리뷰작성자 불러오기 -->
				<td>${rvoc.reviewPostedTime}</td>				<!-- 리뷰작성일 불러오기 -->
				<td>${rvoc.reviewLikes}</td>					<!-- 리뷰추천수 불러오기 -->
				<td>${rvoc.reviewHits}</td>						<!-- 리뷰조회수 불러오기 -->
			</tr>	
		</c:forEach>
	</tbody>
  </table>
<div class="boardBottomDiv">
	<div class="pagingInfo" id="pagingLocation">
		<c:set var="rpbc" value="${requestScope.reviewListByContentsNo.pagingBean}"/>
		<ul class="pagination">
			<c:if test="${rpbc.previousPageGroup}">		<!-- 이전 페이지으로 넘어갈 만큼 충분한 게시글이있으면 '<<' 페이지 넘기기 버튼이 생성된다. -->
			<li><a href="${pageContext.request.contextPath}/contentsDetail.do?pageNo=${rpbc.startPageOfPageGroup-1}&contentsNo=${requestScope.contentsNo}">&laquo;</a></li>	<!-- $laquo는 페이징에 나타나는'<<'왼쪽페이지 버튼이다. -->
			</c:if>
			<c:forEach var="i" begin="${rpbc.startPageOfPageGroup}" end="${rpbc.endPageOfPageGroup}">
				<c:choose>
					<c:when test="${rpbc.nowPage != i}">
						<li><a href="${pageContext.request.contextPath}/contentsDetail.do?pageNo=${i}&contentsNo=${requestScope.contentsNo}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="active"><a href="#">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${rpbc.nextPageGroup}">			<!-- 다음 페이지로 넘어갈 만큼 충분한 게시글이 있으면 '>>' 페이지 넘기기 버튼이 생성된다. -->
				<li><a href="${pageContext.request.contextPath}/contentsDetail.do?pageNo=${rpbc.endPageOfPageGroup+1}&contentsNo=${requestScope.contentsNo}">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
	<div class="writeBtn">
		<div>
			<button type="button" class="btn-default boardBtnSubmit" onclick="location.href = '${pageContext.request.contextPath}/reviewWriteForm.do?contentsNo=${requestScope.contentsNo}' ">리뷰쓰기</button>
		</div>
	</div>
</div>
</div>