<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자_메인페이지</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/reset.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_sideBar.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_main.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/admin_report-post.css">
 

<!-- Bootstrap core JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body class="admin-body">
  <header class="admin-header__main">
    <div class="admin-header__main-container">
      <a class="admin-logo__img" href="${contextPath}"><img src="${contextPath}/resources/img/logo.jpg" alt="logo" class="admin-header__logo"></a>
      <a href="${contextPath}/admin/adminMain"><img  src="${contextPath}/resources/img/admin/유저.jpg" alt="user-profile" class="admin-header__user"></a>
    </div>
  </header>
  
   <main class="admin-main admin_main__main">
       
       <section class="admin-section admin-today">
            <div class="admin-today__visit">
                <form class="cForm" action="${contextPath}/crawler/movie">
                	<div>
	                	<input type="text" id="movieNo" name="movieNo" placeholder="검색할 영화코드 입력">
	                	<button type="submit">영화 업데이트</button>
                	</div>
                </form>
            </div>
                
                <div class="admin-section admin-boards">
                	<div class="updateBox">
	                	<a class="update" href="${contextPath}/crawler/genre" >장르 업데이트</a>
                	</div>
                </div>

       </section>
       <section class="admin-section admin-boards ">
           <div class="admin-board__event">
               <div class="admin-board__header">
                    <span>행사게시판</span>
                    <span><a class="admin-a" href="${contextPath}/eventBoard/list">MORE</a></span>
               </div>
               <!--행사게시판 테이블-->
               <div class="admin-board__table">
                <table class="admin-table table">
                    <colgroup>
                        <col width="10%"/>
                        <col width="90%"/>
                    </colgroup>
                    <thead>
                      <tr>
                        <th scope="col">No</th>
                        <th scope="col">게시글 내용</th>
                      </tr>
                    </thead>

	                 <c:forEach items="${eventBoardList }" var="board" varStatus="b" begin="0" end="4">
	                    <c:choose>
	                    	<c:when test="${empty eventBoardList }">
	                    		<tr>
	                    			<td colspan="2">게시글이 존재하지 않습니다.</td>
	                    		</tr>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<tr>
			                        <th scope="row">${b.count }</th>
			                        <td>
			                            <a class="admin-a" href="${contextPath }/eventBoard/view?no=${board.eventBodNo}&cp=1">${board.eventBodTitle }</a>
			                        </td>
		                        
		                      </tr>
	                    	</c:otherwise>
	                    </c:choose>
                    </c:forEach>
	               </tbody>

                    
                    
                    
                  </table>
               </div>
           </div>
           <div class="admin-board__community">
            <div class="admin-board__header">
                <span>리뷰게시판</span>
                <span><a class="admin-a" href="${contextPath}/reviewBoard/list?type=0">MORE</a></span>
           </div>
               <!--리뷰게시판 테이블-->
               <div class="admin-board__table">
                <table class="admin-table table">
                    <colgroup>
                        <col width="10%"/>
                        <col width="70%"/>
                        <col width="20%"/>
                    </colgroup>
                    <thead>
                      <tr>
                        <th scope="col">No</th>
                        <th scope="col">게시글 내용</th>
                        <th scope="col">작성일시</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${reviewBoardList }" var="board" varStatus="b" begin="0" end="4">
	                    <c:choose>
	                    	<c:when test="${empty reviewBoardList }">
	                    		<tr>
	                    			<td colspan="2">게시글이 존재하지 않습니다.</td>
	                    		</tr>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<tr>
		                        <th scope="row">${b.count }</th>
		                        <td>
		                            <a class="admin-a" href="${contextPath }/reviewBoard/view?type=0&no=${board.reviewNo }&cp=1">${board.reviewTitle }</a>
		                        </td>
		                        <fmt:formatDate var="createDate" value="${board.createDt}"  pattern="yyyy-MM-dd"/>                          
								<fmt:formatDate var="today" value="<%= new java.util.Date() %>"  pattern="yyyy-MM-dd"/>                          
								
									<c:choose>
										<%-- 글 작성일이 오늘이 아닐 경우 --%>
										<c:when test="${createDate != today}">
											<td><fmt:formatDate value="${board.createDt}"  pattern="MM-dd"/></td>
										</c:when>
										
										<%-- 글 작성일이 오늘일 경우 --%>
										<c:otherwise>
											<td><fmt:formatDate value="${board.createDt}"  pattern="HH:mm"/></td>                        
										</c:otherwise>
									</c:choose>
		                      </tr>
	                    	</c:otherwise>
	                    </c:choose>
                    </c:forEach>
                      

                    </tbody>
                  </table>
               </div>
           </div>
       </section>
       <section class="admin-section board">
           <div class="admin-board__report admin-board__report-post">
                <div class="admin-board__header">
                	<span>신고글</span>
                    <span><a class="admin-a" href="${contextPath }/reviewBoard/list?type=200">MORE</a></span>
                </div>
                <!--신고게시글 테이블-->
                <div class="admin-board__table">
                    <table class="admin-table table">
                        <colgroup>
                        	<col width="5%"/>
                            <col width="5%"/>
                            <col width="20%"/>
                            <col width="15%"/>
                            <col width="30%"/>
                            <col width="15%"/>
                            <col width="10%"/>
                        </colgroup>
                        <thead>
                          <tr>
                            <th scope="col">No</th>
                          	<th scope="col">상세</th>
                            <th scope="col">분류</th>
                            <th scope="col">신고 제목</th>
                            <th scope="col">신고 내용</th>
                            <th scope="col">작성자</th>
                            <th scope="col">신고 일자</th>
                          </tr>
                        </thead>
                        <tbody>
                    <c:forEach items="${reportPostList }" var="board" varStatus="b" begin="0" end="4">
	                    <c:choose>
	                    	<c:when test="${empty reportPostList }">
	                    		<tr>
	                    			<td colspan="4">게시글이 존재하지 않습니다.</td>
	                    		</tr>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<tr>
		                        <th scope="row">${b.count }</th>
		                        <td class="reportBtn">
		                        	<a href="${contextPath }/reviewBoard/view?type=201&no=${board.reviewNo}">처리</a>
		                        </td>
		                        <td>${board.reportCdNm }</td>
		                        
								<td>${board.reportTitle }</td>
								
								<td>${board.reportContent}</td>
								
		                        <td>${board.memberNickNm }</td>
		                        
		                        <fmt:formatDate var="createDate" value="${board.reportDate}"  pattern="yyyy-MM-dd"/>                          
								<fmt:formatDate var="today" value="<%= new java.util.Date() %>"  pattern="yyyy-MM-dd"/>                          
								
									<c:choose>
										<%-- 글 작성일이 오늘이 아닐 경우 --%>
										<c:when test="${createDate != today}">
											<td><fmt:formatDate value="${board.reportDate}"  pattern="MM-dd"/></td>
										</c:when>
										
										<%-- 글 작성일이 오늘일 경우 --%>
										<c:otherwise>
											<td><fmt:formatDate value="${board.reportDate}"  pattern="HH:mm"/></td>                        
										</c:otherwise>
									</c:choose>
		                      </tr>
	                    	</c:otherwise>
	                    </c:choose>
                    </c:forEach>
                      

                    </tbody>
                      </table>
                </div>
           </div>
       </section>
       
       <section class="admin-section board">
           <div class="admin-board__complaint">
                <div class="admin-board__header">
                    <span>불편사항</span>
                    <span><a class="admin-a" href="${contextPath}/complainBoard/list?type=0">MORE</a></span>
                </div>
                <!--불편사항게시판 테이블-->
                <div class="admin-board__table">
                    <table class="admin-table table">
                        <colgroup>
                            <col width="5%"/>
                            <col width="20%"/>
                            <col width="45%"/>
                            <col width="20%"/>
                            <col width="10%"/>
                        </colgroup>
                        <thead>
                          <tr>
                            <th scope="col">No</th>
                            <th scope="col">유형</th>
                            <th scope="col">게시글 내용</th>
                            <th scope="col">작성자</th>
                            <th scope="col">작성 일자</th>
                          </tr>
                        </thead>
                        <tbody>
                    <c:forEach items="${complainBoardList }" var="board" varStatus="b" begin="0" end="4">
	                    <c:choose>
	                    	<c:when test="${empty complainBoardList }">
	                    		<tr>
	                    			<td colspan="4">게시글이 존재하지 않습니다.</td>
	                    		</tr>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<tr>
		                        <th scope="row">${b.count }</th>
		                        <td>${board.categoryName }</td>
		                        <td>
		                            <a class="admin-a" href="${contextPath }/complainBoard/view?type=0&no=${board.complainNo }&cp=1">${board.complainTitle }</a>
		                        </td>
		                        <td>${board.memberNickNm }</td>
		                        <fmt:formatDate var="createDate" value="${board.complainDate}"  pattern="yyyy-MM-dd"/>                          
								<fmt:formatDate var="today" value="<%= new java.util.Date() %>"  pattern="yyyy-MM-dd"/>                          
								
									<c:choose>
										<%-- 글 작성일이 오늘이 아닐 경우 --%>
										<c:when test="${createDate != today}">
											<td><fmt:formatDate value="${board.complainDate}"  pattern="MM-dd"/></td>
										</c:when>
										
										<%-- 글 작성일이 오늘일 경우 --%>
										<c:otherwise>
											<td><fmt:formatDate value="${board.complainDate}"  pattern="HH:mm"/></td>                        
										</c:otherwise>
									</c:choose>
		                      </tr>
	                    	</c:otherwise>
	                    </c:choose>
                    </c:forEach>
                      

                    </tbody>
                      </table>
                </div>
           </div>
       </section>
   </main>
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
    
  <script>
    var date = new Date();
    var year = date.getFullYear(date);
    $(document).ready(function(){
      $("#currentYear").text(year);
    });
    
	

  </script>
</body>
</html>