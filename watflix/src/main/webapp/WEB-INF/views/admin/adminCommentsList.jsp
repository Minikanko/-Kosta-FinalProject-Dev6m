<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript">
	$(document).ready(function(){
		// 게시물 전체 선택 (삭제) 
		$("#checkAll").click(function(){
			if($(this).is(":checked")){
				$("input[type=checkbox]").prop("checked", true);
			} else {
				$("input[type=checkbox]").prop("checked", false);
			}
		})
		// 일괄 삭제 (선택된 글이 없으면 alert을 호출한다.)
		$("#deleteNoticeByCheckboxForm").submit(function(){
			var deleteCount = $("input[name='deleteCheckbox']:checked").length;
			if(deleteCount == 0){
				alert("삭제할 글을 선택해주세요.");
				return false;
			} else {
				return confirm("삭제하시겠습니까?");
			}					
		})
		$(".starPointImg").each(function(){
				var starPoint = $(this).html();
				$(this).html("<img src='${pageContext.request.contextPath}/resources/media/icons/star"+starPoint+".png' style='height: 25px'>");
		})
	})
	// delete confirm
	function commentsDeleteConfirm(){
		return confirm('삭제하시겠습니까?');
	}	
</script>
	<div class="container-lg margin-top margin-bottom" style="margin:100px auto;">
	<div class="container boardClassMain" style="border-radius: 1.5px;">
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<%-- <sec:authentication property="principal.id" var="userId"/> --%>
		<%-- <sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin"/> --%>
		<form action="${pageContext.request.contextPath}/commentsDeleteByCheckbox.do"
		id="deleteNoticeByCheckboxForm" method="post">	
		<sec:csrfInput/>
		<h3 style="display: inline-flex;">전체 한줄평</h3><a href="commentsList.do">더보기</a>
		<table class="table table-hover table-bordered" style="border-radius: 1.5px;">
			<c:forEach items="${requestScope.commentsList.list}" var="commentsList">
			<tr>
				<td class="starPointImg">${commentsList.commentsStars}</td>
				<td>${commentsList.commentsStars}</td>
				<td>
					${commentsList.comments}<br>
					${commentsList.memberVO.id }
					${commentsList.commentsPostedTime}				
				</td>
				<td><input type="checkbox" value="${commentsList.commentsNo}" name="deleteCheckbox"></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="3"></td>
				<td>
					<input type="hidden" name="pageNo" value="${requestScope.commentsList.pagingBean.nowPage}">
					<input type="submit" value="삭제">
				</td>
			</tr>
		</table>
		</form>
		<!--
		전체게시물조회 메인화면에서 페이징과 버튼을 사용하지 않기 위해 사용한다.
		tiles를 통해서 불러오는 리스트의 경우 forNotUsePagingAndBtn의 값은 true
		-->
		<c:if test="${requestScope.forNotUsePagingAndBtn != true}">
	<div class="boardBottomDiv">
		<div class="pagingInfo" id="pagingLocation">
			<c:set var="pagingBean" value="${requestScope.commentsList.pagingBean}"/>
			<ul class="pagination">
				<c:if test="${pagingBean.previousPageGroup}">
					<li><a href="${pageContext.request.contextPath}/getCommentsList.do?pageNo=${pagingBean.startPageOfPageGroup-1}">&laquo;</a></li>
				</c:if>
				<c:forEach var="i" begin="${pagingBean.startPageOfPageGroup}" end="${pagingBean.endPageOfPageGroup}">
					<c:choose>
						<c:when test="${pagingBean.nowPage!=i}">
							<li><a href="${pageContext.request.contextPath}/getCommentsList.do?pageNo=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="active"><a href="#">${i}</a></li>
						</c:otherwise>
					</c:choose>
					&nbsp;
				</c:forEach>
					<c:if test="${pagingBean.nextPageGroup}">
						<li><a href="${pageContext.request.contextPath}/getCommentsList.do?pageNo=${pagingBean.endPageOfPageGroup+1}">&raquo;</a></li>
					</c:if>
			</ul>
		</div>
		</div>
	</c:if>
	</sec:authorize>
	</div>
</div>