<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자_회원관리페이지</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- Bootstrap core JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/reset.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_sideBar.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_boardList.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_member.css">
</head>
<body class="admin-body">
    <header class="admin-header__main">
        <div class="admin-header__main-container">
          
	      <a class="admin-logo__img" href="${contextPath}"><img src="${contextPath}/resources/img/logo.jpg" alt="logo" class="admin-header__logo"></a>
	      <a href="${contextPath}/admin/adminMain"><img  src="${contextPath}/resources/img/admin/유저.jpg" alt="user-profile" class="admin-header__user"></a>
        </div>
      </header>
    <main class="admin-main admin-main__member">
        <section class="admin-member__page-title admin-section">
            <h1>회원 관리</h1>
  
        </section>
        <section class="admin-member__searchForm-container admin-section">
            <form action="${contextPath }/admin/adminMember/list?type=1" class="admin-member__searchForm" method="post" onsubmit="return boardValidate();">
                <input class="admin-member__radio" type="radio" id="memberId" name="search" value="1" checked>
                <label for="searchId" class="admin-member__margin-right admin-member__label">아이디</label>
                <input class="admin-member__radio" type="radio" id="memberNickname" name="search" value="2">
                <label for="searchNickname" class="admin-member__margin-right admin-member__label">닉네임</label>
                <input class="admin-member__search-input admin-member__margin-right" type="text" name="searchInput" placeholder="검색할 아이디/닉네임 입력" >
               

                <select name="condition" class="admin-member__margin-right admin-member__select">
                    <option value="Y" name="active" selected>활동중</option>
                    <option value="N" name="drop">탈퇴</option>

                </select>
                <button type="submit" value="searchBtn" class="admin-member__btn">조회</button>
            </form>
        </section>
        <section class="admin-member__board__table admin-section">
            <table class="admin-member__table table">
                <colgroup>
                    <col width="10%"/>
                    <col width="15%"/>
                    <col width="15%"/>
                    <col width="30%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                </colgroup>
                <thead>
                  <tr>
                    <th scope="col">회원번호</th>
                    <th scope="col">아이디</th>
                    <th scope="col">닉네임</th>
                    <th scope="col">가입일</th>
                    <th scope="col">등급</th>
                    <th scope="col">탈퇴여부</th>
                  </tr>
                </thead>
                
                <%-- 게시글 목록 출력 --%>
					<tbody>
						<%-- if else 문 --%>
						<c:choose> 
						
							<%-- 조회된 게시글 목록이 없는 경우 --%>
							<c:when test="${empty memberList }">
								<tr>
									<td colspan="6">게시글이 존재하지 않습니다</td>
								</tr>
								
							</c:when>
							<%-- 조회된 게시글 목록이 있는 경우 --%>
							<c:otherwise>
								<c:forEach items="${memberList}" var="member" varStatus="mem">
									<tr>
										<%-- 회원번호 --%>
										<td>${member.memberNo }</td>
										<%-- 아이디 --%>
										<td>${member.memberId }</td>
										
										<%-- 닉네임 --%>
										<td>${member.memberNickNm }</td>
										
										
										<%-- 가입일 --%>
										<td>${member.signUpDt }</td>
										
										<%-- 등급 --%>
										<c:choose>
											<c:when test="${member.memberGrade== 'A'}">
												<td>관리자</td>
											</c:when>
											<c:otherwise>
												<td>일반회원</td>
											</c:otherwise>
										</c:choose>
										
										
										<%-- 탈퇴여부 --%>
										<c:choose>
											<c:when test="${member.memberStatus == 'Y'}">
												<td>활동중</td>
											</c:when>
											<c:otherwise>
												<td>탈퇴</td>
											</c:otherwise>
										</c:choose>
										
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>


					
					</tbody>
              </table>
        </section>
        
        
			
        <section class="admin-boardList-nav admin-section">
            <%---------------------- Pagination start----------------------%>
			<%-- 페이징 처리 시 주소를 쉽게 작성할 수 있도록 필요한 변수를 미리 선언 --%>
			<c:choose>
				<c:when test="${ !empty searchType}">
					<c:set var="pageURL" value="list?type=${pagination.boardType }&searchInput=${searchInput }&searchType=${searchType }&condition=${condition }"></c:set>
				</c:when>
				
				<c:otherwise>
					<c:set var="pageURL" value="list?type=${pagination.boardType }"></c:set>
				</c:otherwise>
			</c:choose>
			
			<c:set var="prev" value="${pageURL}&cp=${pagination.prevPage }"></c:set>
			<%-- 다음페이지 시작주소 --%>
			<c:set var="next" value="${pageURL}&cp=${pagination.nextPage }"></c:set>
			
			<div class="my-5">
				<ul class="pagination">
					
					<%-- 현재 페이지가 페이지사이즈 초과인 경우 --%>
					<c:if test="${pagination.currentPage > pagination.pageSize }">
						<li>
							<a href="${prev }"><i class="fas fa-angle-left"></a>
						</li>
					</c:if>
					
					<%-- 현재 페이지가 2페이지 초과인 경우 --%>
					<c:if test="${pagination.currentPage > 2 }">
						<li>
							<a href="${pageURL}&cp=${pagination.currentPage-1}"><i class="fas fa-angle-left"></a>
						</li>
					</c:if>
					
					
					
					<%-- 페이지 목록 --%>
					<c:forEach var="p" begin="${pagination.startPage}" end="${pagination.endPage}">
						
							<c:choose>
								<c:when test="${p == pagination.currentPage }">
									<li><a>${p }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageURL}&cp=${p}">${p}</a></li>
								</c:otherwise>
							</c:choose>
					
					</c:forEach>
					
					
					
					
					
					
					<%-- 현재 페이지가 마지막 페이지 미만인 경우 --%>
					<c:if test="${pagination.currentPage < pagination.maxPage }">
						<li>
							<a href="${pageURL}&cp=${pagination.currentPage+1}"><i class="fas fa-angle-right"></i></a>
						</li>
					</c:if>
					
					<%-- 현재 페이지가 마지막 페이지 미만인 경우 --%>
					<c:if test="${pagination.currentPage - pagination.maxPage + pagination.pageSize < 0 }">
						<li>
							<a href="${next}"><i class="fas fa-angle-right"></a>
						</li>
					</c:if>
	
				</ul>
			</div>
			<%---------------------- Pagination end----------------------%>
        </section>
        <script src="https://kit.fontawesome.com/9e8d2d5fe7.js" crossorigin="anonymous"></script>
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
           <li><a class="admin-a" href="${contextPath}/eventBoard/list">행사게시판</a></li>
           <li><a class="admin-a" href="${contextPath}/FAQBoard/list">공지사항</a></li>
           <li><a class="admin-a" href="${contextPath}/complainBoard/list?type=0">불편사항</a></li>
        </ul>
    </div>
    
    
    <c:if test="${!empty title }">
      <script>
         swal({
            "icon" : "${icon}",
            "title" : "${title}",
            "text" : "${text}"
         });
      </script>


      <c:remove var="icon" />
      <c:remove var="title" />
      <c:remove var="text" />
   </c:if>
   <script>
	// 유효성 검사 
		function boardValidate() {
			if ($(".admin-member__search-input").val().trim().length == 0) {
				alert("회원 정보를 입력해 주세요.");
				$(".admin-member__search-input").focus();
				return false;
			}
	
			if ($("#boardContent").val().trim().length == 0) {
				alert("내용을 입력해 주세요.");
				$("#content").focus();
				return false;
			}
			
			if($("#movieCategory").val() == null) {
				alert("영화를 선택해주세요.");
				$("#keyword").focus();
				return false;
			}
		}
   
   </script>
</body>
</html>