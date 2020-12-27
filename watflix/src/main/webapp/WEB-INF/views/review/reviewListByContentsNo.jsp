<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 
<script type="text/javascript">
	$(document).ready(function(){
		//유저의 아이디로 작성한 review의 여부를 확인한다.
		//리뷰가 있을 경우  alert를 호출한다.
		//리뷰가 없을 경우 작성하기로 간다.
		$("#checkReviewExist").click(function(){
			var contentsNo = "${requestScope.contentsNo}";
			$.ajax({
				url: "${pageContext.request.contextPath}/checkReviewExist.do",
				type: "GET",
				data: 'contentsNo='+contentsNo,
				success: function(data){
					if(data=="no"){
						alert("이미 작성하신 리뷰가 존재합니다.한 컨텐츠에 하나의 리뷰만 작성하실 수 있습니다.");
					}else if(data=="yes"){
						location.href="${pageContext.request.contextPath}/reviewWriteForm.do?contentsNo=${requestScope.contentsNo}";
					}
				}
			});//ajax
		});//click
		$("#loginbutton").click(function(){
			location.href="${pageContext.request.contextPath}/loginForm.do";
		})
	});//ready
</script>
<div class="tableMargin" id="reviewList">
	<div class="container-lg boardClassMain">
	  <h4 style="display: inline-flex;">리뷰</h4> 
	  <c:choose>
	  	<c:when test="${requestScope.reviewListByContentsNo.reviewList[0] == null}"><br>
	  		<span>현재 해당 컨텐츠에는 리뷰가 존재하지 않습니다.</span><br>
  			 <span>첫 번째 리뷰어가 되어보세요!</span>
  			 <sec:authorize access="isAnonymous()">
				<button type="button" id="loginbutton" style="width:80px; float:right;">리뷰쓰기</button>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<button type="button" id="checkReviewExist" style="width:80px; float:right;">리뷰쓰기</button>
			</sec:authorize>
			
	  	</c:when>
	  	<c:otherwise>
	  		 <table class="table table-hover table-bordered" style="border-radius: 1.5px;">
			    <thead>
			      <tr>
					<th class="th_title">제목</th>
					<th class="th_author">작성자</th>
					<th class="th_date">작성일</th>
					<th class="th_likes">추천</th>
					<th class="th_hits">조회</th>
			      </tr>
			    </thead>
			    <tbody>
					<c:forEach var="rvoc" items="${requestScope.reviewListByContentsNo.reviewList}">
						<tr>
							<!-- 리뷰제목 불러오기 -->
							<td class="td_title"><a href="${pageContext.request.contextPath}/reviewDetail.do?reviewNo=${rvoc.reviewNo}">${rvoc.reviewTitle}</a></td>
							<td class="td_author">${rvoc.memberVO.id}</td>					<!-- 리뷰작성자 불러오기 -->
							<td class="td_date">${rvoc.reviewPostedTime}</td>				<!-- 리뷰작성일 불러오기 -->
							<td class="td_likes">${rvoc.reviewLikes}</td>					<!-- 리뷰추천수 불러오기 -->
							<td class="td_hits">${rvoc.reviewHits}</td>						<!-- 리뷰조회수 불러오기 -->
						</tr>	
					</c:forEach>
				</tbody>
			  </table>
			 <sec:authorize access="isAnonymous()">
				<button type="button" id="loginbutton" style="width:80px; float:right;">리뷰쓰기</button>
			</sec:authorize>
	  		<sec:authorize access="isAuthenticated()">
				<button type="button" id="checkReviewExist" style="width:80px; float:right;">리뷰쓰기</button>
			</sec:authorize>

			  			
				<!-- 페이징박스 시작 -->
				<div class="boardBottomDiv">
					<div class="pagingInfo" id="pagingLocation">
						<c:set var="rpbc" value="${requestScope.reviewListByContentsNo.pagingBean}"/>
						<ul class="pagination">
							<c:if test="${rpbc.previousPageGroup}">		<!-- 이전 페이지으로 넘어갈 만큼 충분한 게시글이있으면 '<<' 페이지 넘기기 버튼이 생성된다. -->
							<li><a href="${pageContext.request.contextPath}/contentsDetail.do?reviewPageNo=${rpbc.startPageOfPageGroup-1}&contentsNo=${requestScope.contentsNo}">&laquo;</a></li>	<!-- $laquo는 페이징에 나타나는'<<'왼쪽페이지 버튼이다. -->
							</c:if>
							<c:forEach var="i" begin="${rpbc.startPageOfPageGroup}" end="${rpbc.endPageOfPageGroup}">
								<c:choose>
									<c:when test="${rpbc.nowPage != i}">
										<li><a href="${pageContext.request.contextPath}/contentsDetail.do?reviewPageNo=${i}&contentsNo=${requestScope.contentsNo}">${i}</a></li>
									</c:when>
									<c:otherwise>
										<li class="active"><a href="#">${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${rpbc.nextPageGroup}">			<!-- 다음 페이지로 넘어갈 만큼 충분한 게시글이 있으면 '>>' 페이지 넘기기 버튼이 생성된다. -->
								<li><a href="${pageContext.request.contextPath}/contentsDetail.do?reviewPageNo=${rpbc.endPageOfPageGroup+1}&contentsNo=${requestScope.contentsNo}">&raquo;</a></li>
							</c:if>
						</ul>
					</div>
				</div>
				<!-- 페이징박스 끝 -->
	  	</c:otherwise>
	  </c:choose>         
	</div>
</div>