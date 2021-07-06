<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자_신고게시글페이지</title>
    
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- Bootstrap core JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
     <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/reset.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_sideBar.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_boardList.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_report-post.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_style.css">

</head>
<body class="admin-body">
    <header class="admin-header__main">
        <div class="admin-header__main-container">
          <img href="#" src="${contextPath}/resources/img/admin/로고.png" alt="logo" class="admin-header__logo">
          <img href="#"  src="${contextPath}/resources/img/admin/유저.jpg" alt="user-profile" class="admin-header__user">
        </div>
      </header>
    <main class="admin-main">
        <section class="admin-post__page-title admin-post__section">
            <h1>신고 게시글</h1>
        </section>
        <section class="admin-post__section">
            
        

                <table class="admin-post__table table">
                    <colgroup>
                        <col width="10%"/>
                        <col width="7%"/>
                        <col width="15%"/>
                        <col width="12%"/>
                        <col width="20%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                    </colgroup>
                    <thead>
                      <tr>

                        <th scope="col">상세</th>
                        <th scope="col">No</th>
                        <th scope="col">신고유형</th>
                        <th scope="col">신고제목</th>
                        <th scope="col">신고내용</th>
                        <th scope="col">작성자</th>
                        <th scope="col">신고일자</th>
                      </tr>
                    </thead>
                    <%-- 게시글 목록 출력 --%>
						<tbody>
						
							
							<%-- 게시글 if else 문 --%>
							<c:choose> 
								
								<%-- 조회된 게시글 목록이 없는 경우 --%>
								<c:when test="${empty boardList }">
									<tr>
										<td colspan="6">게시글이 존재하지 않습니다</td>
									</tr>
									
								</c:when>
								<%-- 조회된 게시글 목록이 있는 경우 --%>
								<c:otherwise>
									<c:forEach items="${boardList }" var="board" varStatus="b">
										<tr>
											<%-- 처리번호 --%>
											<th>
												
													<a href="${contextPath }/reviewBoard/view?type=201&no=${board.reviewNo}">확인</a>
												
											</th>
											<%-- 글번호 --%>
											<th>${b.count }</th>
											<%-- 신고유형 --%>
											<td>${board.reportCdNm}</td>
											<%-- 신고제목 --%>
											<td>${board.reportTitle }</td>
											<%-- 신고내용 --%>
											<td>${board.reportContent}</td>
											<%-- 작성자 --%>
											<td>${board.memberNickNm }</td>
											<%-- 작성일 --%>
											<td>
												
												<fmt:formatDate var="createDate" value="${board.reportDate}"  pattern="yyyy-MM-dd"/>                          
												<fmt:formatDate var="today" value="<%= new java.util.Date() %>"  pattern="yyyy-MM-dd"/>                          
												
												<c:choose>
													<%-- 글 작성일이 오늘이 아닐 경우 --%>
													<c:when test="${createDate != today}">
														${createDate}
													</c:when>
													
													<%-- 글 작성일이 오늘일 경우 --%>
													<c:otherwise>
														<fmt:formatDate value="${board.reportDate}"  pattern="HH:mm"/>                          
													</c:otherwise>
												</c:choose>
												
											</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
	
							
							
						
						</tbody>
                    </table>
		

                    
								
								
								
			</section>
								<%---------------------- Pagination start----------------------%>
								<%-- 페이징 처리 시 주소를 쉽게 작성할 수 있도록 필요한 변수를 미리 선언 --%>
								
								
								
								<c:choose>
									<c:when test="${ !empty searchType}">
										<c:set var="pageURL" value="list?type=${pagination.boardType }&searchValue=${searchValue }&searchType=${searchType }"></c:set>
									</c:when>
									
									<c:otherwise>
										<c:set var="pageURL" value="list?type=${pagination.boardType }"></c:set>
									</c:otherwise>
								</c:choose>
								
								<c:set var="prev" value="${pageURL}&cp=${pagination.prevPage }"></c:set>
								<%-- 다음페이지 시작주소 --%>
								<c:set var="next" value="${pageURL}&cp=${pagination.nextPage }"></c:set>
								
								<section class="admin-boardList-nav admin-post__section">
									<ul>
										
										<%-- 현재 페이지가 페이지사이즈 초과인 경우 --%>
										<c:if test="${pagination.currentPage > pagination.pageSize }">
											<li>
												<a class="admin-a" href="${prev }"><i class="fas fa-angle-left"></a>
											</li>
										</c:if>
										
										<%-- 현재 페이지가 2페이지 초과인 경우 --%>
										<c:if test="${pagination.currentPage > 2 }">
											<li>
												<a class="admin-a" href="${pageURL}&cp=${pagination.currentPage-1}"><i class="fas fa-chevron-left"></i></a>
											</li>
										</c:if>
										
										
										
										<%-- 페이지 목록 --%>
										<c:forEach var="p" begin="${pagination.startPage}" end="${pagination.endPage}">
											
												<c:choose>
													<c:when test="${p == pagination.currentPage }">
														<li><a class="admin-a" >${p }</a></li>
													</c:when>
													<c:otherwise>
														<li><a class="admin-a" href="${pageURL}&cp=${p}">${p}</a></li>
													</c:otherwise>
												</c:choose>
										
										</c:forEach>
										
										
										
										
										
										
										<%-- 현재 페이지가 마지막 페이지 미만인 경우 --%>
										<c:if test="${pagination.currentPage < pagination.maxPage }">
											<li>
												<a class="admin-a" href="${pageURL}&cp=${pagination.currentPage+1}"><i class="fas fa-chevron-right"></i></a>
											</li>
										</c:if>
										
										<%-- 현재 페이지가 마지막 페이지 미만인 경우 --%>
										<c:if test="${pagination.currentPage - pagination.maxPage + pagination.pageSize < 0 }">
											<li>
												<a class="admin-a"  href="${next}"><i class="fas fa-angle-right"></a>
											</li>
										</c:if>
					
									</ul>
								</div>
								<%---------------------- Pagination end----------------------%>
                           
                        
                  			</section>
          
 
    </main>
    <div id="footer">
        <hr>
        <div class="d-flex bd-highlight" id="maincustomerservice">
          <div class='d-flex bd-highlight mr-sm-4'><a href="#">서비스 이용약관</a></div>
          <div class='d-flex bd-highlight mr-sm-4'><a href="#">개인정보처리방침</a></div>
          <div class='d-flex bd-highlight mr-sm-4'><a href="#">고객센터</a></div>
        </div>
        <hr>
        <div class="main-footer__pre">
          <pre>
            (04540) 서울특별시 중구 xxxx 888 xxxx
            TEL. 02)8888-8888 | FAX. 02)888-8888 | 사업자등록번호 : xxx-xx-xxxx | 기관명 : xxxxxx | 대표자 : xxx
            
            Copyright © 2021-<span id="currentYear"></span> xx xxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxx All Right Reserved
        </pre>
        </div>
      </div>
    <div class="admin-side">
        <div class="admin-side__profile"></div>
        <ul>
          <li><a class="admin-a" href="${contextPath}/admin/adminMain">관리자 메인</a></li>
           <li><a class="admin-a" href="${contextPath }/admin/adminMember/list?type=0">회원관리</a></li>
           <li><a class="admin-a" href="${contextPath }/reviewBoard/list?type=200">신고 게시글</a></li>
           <li><a class="admin-a" href="${contextPath }/reviewBoard/list?type=300">신고 댓글</a></li>
           <li><a class="admin-a" href="#">행사게시판</a></li>
           <li><a class="admin-a" href="#">커뮤니티</a></li>
           <li><a class="admin-a" href="${contextPath}/complainBoard/list?type=0">불편사항</a></li>
        </ul>
    </div>
    
    <%-- 로그인 실패와 같은 메세지가 서버로부터 전달되어 온 경우 출력 --%>
	<c:if test="${!empty title }">
		<script>
			swal({
				"icon" : "${icon}", 
				"title" : "${title}",
				"text" : "${text}"
			})
		</script>
		
		<%-- 특정 스코프에 있는 속성(변수)를 제거할 수 잇음 --%>
		<%-- 서버로부터 전달받은 메세지를 1회 출력 후 제거 -> 반복 출력되지 않음 --%>
		<c:remove var="icon"/>
		<c:remove var="title"/>
		<c:remove var="text"/>
	</c:if>
    <script src="https://kit.fontawesome.com/9e8d2d5fe7.js" crossorigin="anonymous"></script>
</body>
</html>