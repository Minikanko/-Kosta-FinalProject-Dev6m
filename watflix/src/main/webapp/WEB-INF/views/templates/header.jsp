<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script type="text/javascript">
	 $(document).ready(function(){
		 if(${navBarTab=='HOME'}){
			 $("#HOME").parent().addClass("active");
			 
		 }
		 else if(${navBarTab=='TV'}){
			 $("#TV").parent().addClass("active");
		 }
		 else if(${navBarTab=='MOVIE'}){
			 $("#MOVIE").parent().addClass("active");
		 }
		 $(".nav-toggle").click(function(){
			 $("#sidebar-wrapper").toggleClass("active")
			 $("#mypageDiv").css('display','none');
		 });
		 
		 /* 권한이 있을때에만 로그아웃이벤트 활성화 */
		 <sec:authorize access="hasRole('ROLE_MEMBER')">
		 $("#logoutBtn").click(function() {
				$("#logoutForm").submit();
			});
		 
		 $("#mypageBtn").click(function(){
			 $("#mypageDiv").toggle('slow');
		 })
		 </sec:authorize>
	 });
	 
	//컨텐츠 검색
	function searchContentsTitle(){
		 if($("#contentsTitle").prop("type") == "hidden"){
			$("#contentsTitle").prop("type", "text");
		}else if($("#contentsTitle").val().length >= 2) {
			location.href="${pageContext.request.contextPath}/contentsByTitle.do?contentsTitle="+$("#contentsTitle").val();
		}else{
			alert("두 글자 이상부터 검색 가능")
		}
	 };
</script>
  <!-- Navbar - OPEN -->
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark" id="navbar">
        <!-- Navbar content - OPEN -->
        <div class="container-lg">
            <!-- Logo - OPEN -->
            <a class="navbar-brand logo" href="home.do">
                <img src="${pageContext.request.contextPath}/resources/media/images/logo.png" width="80" alt="">
            </a>
            <!-- Logo - CLOSE -->

            <!-- Toogle responsive - OPEN -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- Toogle responsive - CLOSE -->

            <!-- Content collapse - OPEN -->
            <div class="collapse navbar-collapse" id="navbarToggler">

                <!-- Left - OPEN -->
                <ul class="navbar-nav mr-auto my-2 my-lg-0" id="navTab">
                    <li class="nav-item">
                        <a class="nav-link navbar-nav-item" id="HOME" href="${pageContext.request.contextPath}/home.do">
                            홈
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link navbar-nav-item" id="TV" href="${pageContext.request.contextPath}/contentsByTV.do">
                            TV프로그램
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link navbar-nav-item"  id="MOVIE" href="${pageContext.request.contextPath}/contentsByMovie.do">
                            영화
                        </a>
                        
                    </li>
                </ul>
                <!-- Left - CLOSE -->

                <!-- Right - OPEN -->
                <ul class="navbar-nav my-2 my-lg-0 navbar-right">
                	<!-- 검색 키워드 입력 -->
                	<li class="nav-item">
                		<!-- if(event.keyCode == 13) 엔터키 입력시 이벤트 발생 -->
                		<input type="hidden" id="contentsTitle" onkeypress="javascript:if(event.keyCode == 13){searchContentsTitle();}" style="width:300px;height:30px;font-size:15px;margin-top:15px;">
                	</li>
                	<!-- 컨텐츠 title 검색  -->
                    <li class="nav-item">
                        <a class="nav-link float-center" id="searchContents" href="#" onclick="searchContentsTitle()">
                            <img src="${pageContext.request.contextPath}/resources/media/icons/search.png" width="20" alt="">
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link float-center" href="#">
                            <img src="${pageContext.request.contextPath}/resources/media/icons/bell.png" width="20" alt="">
                        </a>
                    </li>
                     <li class="nav-item nav-toggle">
                        <a class="nav-link float-center" href="#">
                        <span class="navbar-toggler-icon"></span>
                        </a>
                    </li>			
                </ul>
                <!-- Right - OPEN -->
           
	
            </div>
            <!-- Content collapse - CLOSE -->
            <!-- Home Menu - OPEN -->
			 <nav id="sidebar-wrapper">
			    <ul class="sidebar-nav">
			      <li class="sidebar-brand" style="background-color:black;">
			        메뉴
			      </li>
			      <!--로그인 여부 판단  -->
			      	<sec:authorize access="!hasRole('ROLE_MEMBER')">
						<li class="sidebar-nav-item">
						  <a class="js-scroll-trigger" href="${pageContext.request.contextPath}/loginForm.do">로그인/회원가입</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_MEMBER')">
						<li class="sidebar-nav-item">
						   <a class="js-scroll-trigger" href="#" id="logoutBtn">로그아웃</a>
						   <form id="logoutForm" action="${pageContext.request.contextPath}/logout.do" method="post" style="display: none">
						<sec:csrfInput />
						</form>
						 </li>
						 <li class="sidebar-nav-item">
						  <a class="js-scroll-trigger" href="#" id="mypageBtn">마이페이지</a>
						  <!-- 하위 항목 -->
						  <div class="mypageDiv" id="mypageDiv">
						  	<ul class="mypageList">
						  		<li><a href="${pageContext.request.contextPath}/memberUpdateForm.do">- 회원정보수정</a></li>
						  		<li><a href="${pageContext.request.contextPath}/memberPointHistoryCheck.do">- 포인트</a></li>
						  		<li><a href="${pageContext.request.contextPath}/memberProductOrderList.do">- 구매내역조회</a></li>
						  		<!-- 임시로 경로 설정 -->
						  		<li><a href="${pageContext.request.contextPath}/myPostList.do">- 내 게시물 조회</a></li>
						  		<li><a href="${pageContext.request.contextPath}/qnaList.do">- 1:1문의</a></li>
						  	</ul>
						  </div>
						</li>
					</sec:authorize>
			      <li class="sidebar-nav-item">
			        <a class="js-scroll-trigger" href="${pageContext.request.contextPath}/partyList.do">파티게시판</a>
			      </li>
			      <li class="sidebar-nav-item">
			        <a class="js-scroll-trigger" href="${pageContext.request.contextPath}/getNoticeList.do">공지사항</a>
			      </li>
			      <li class="sidebar-nav-item">
			        <a class="js-scroll-trigger" href="${pageContext.request.contextPath}/faqList.do">고객센터</a>
			      </li>
			      <li class="sidebar-nav-item">
			        <a class="js-scroll-trigger" href="${pageContext.request.contextPath}/productMarket.do">포인트마켓</a>
			      </li>
			      <!-- 관리자만 볼수 있도록 코딩해야함 -->
			      <li class="sidebar-nav-item">
			        <a class="js-scroll-trigger" href="${pageContext.request.contextPath}/reportReviewBoard.do">신고게시판</a>
			      </li>
			      			     	 <sec:authorize access="hasRole('ROLE_ADMIN')">
						<li class="sidebar-nav-item">
				        	<a class="js-scroll-trigger" href="${pageContext.request.contextPath}/adminHome.do" >관리자 페이지</a>
				      	</li>
					</sec:authorize>
			    </ul>
 			 </nav>
		   <!-- Home Menu - CLOSE -->
        </div>
        <!-- Navbar content - CLOSE -->
                    
    </nav>
    <!-- Navbar - CLOSE -->
     
