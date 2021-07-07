<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>09_mypage_secession</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<style>

</style>

<body>
	<jsp:include page="../../../views/common/header.jsp" />
  	<jsp:include page= "menu/mypageMenu.jsp" />
  <div class="mypagemain">
    <div> <h3>게시글 관리</h3> </div>
    <hr>
  </div>
  
  <div class="container my-5">
		
		<h1>${pagination.boardName} 게시판</h1>
			<div class="list-wrapper">
				<table class="table table-hover table-striped my-5" id="list-table">
					<thead>
						<tr>
							<th>글번호</th>
							<th>카테고리</th>
							<th>제목</th>
							<th>조회수</th>
							<th>작성일</th>
              <th>체크박스</th>
						</tr>
					</thead>
					
					<%-- 게시글 목록 출력 --%>
					<tbody>
						
						<c:choose>
							
							<%-- 조회된 게시글 목록이 없는 경우 --%>
							<c:when test="${empty boardList}">
								<tr>
									<td colspan="6">게시글이 존재하지 않습니다.</td>
								</tr>								
							</c:when>
							
							
							<%-- 조회된 게시글 목록이 있을 경우 --%>
							<c:otherwise>
							
								<c:forEach items="${boardList}" var="board">
									<tr>
										<%-- 글 번호 --%>
										<td> ${board.boardNo} </td>
										
										<%-- 카테고리 --%>
										<td> ${board.categoryName} </td>
										
										<%-- 글 제목 --%>
										<td class="boardTitle">                                                         
											<a href="view?no=${board.boardNo}&cp=${pagination.currentPage}&type=${pagination.boardType}">
										
										<%-- 조회수 --%>
										<td> ${board.readCount} </td>
										
										<teㅇ%-- 작성일 --%>
										<td> 
											<fmt:formatDate var="createDate" value="${board.createDate}"  pattern="yyyy-MM-dd"/>                          
											<fmt:formatDate var="today" value="<%= new java.util.Date() %>"  pattern="yyyy-MM-dd"/>                          
											
											<c:choose>
												<%-- 글 작성일이 오늘이 아닐 경우 --%>
												<c:when test="${createDate != today}">
													${createDate}
												</c:when>
												
												<%-- 글 작성일이 오늘일 경우 --%>
												<c:otherwise>
													<fmt:formatDate value="${board.createDate}"  pattern="HH:mm"/>                          
												</c:otherwise>
											</c:choose>
										</td>
                    <td>
                      <input type="checkbox" value="aaa">
                    </td>
									</tr>
								</c:forEach>
							
							</c:otherwise>
						
						</c:choose>

					
					</tbody>
				</table>
			</div>

			<c:if test="${!empty loginMember }">
				<button type="button" class="btn btn-primary float-right" id="insertBtn"
				 onclick="location.href='../board2/insertForm?type=${pagination.boardType}';">삭제</button>
			</c:if>
			
				
			<c:set var="pageURL" value="list?type=${pagination.boardType}"  />
			
			<c:set var="prev" value="${pageURL}&cp=${pagination.prevPage}" />
			<c:set var="next" value="${pageURL}&cp=${pagination.nextPage}" />
			
			
			<div class="my-5">
				<ul class="pagination">
				
					<c:if test="${pagination.currentPage > pagination.pageSize }">
						<li><a class="page-link" href="${prev}">&lt;&lt;</a></li>
					</c:if>
					
					<c:if test="${pagination.currentPage > 2 }">
						<li><a class="page-link" href="${pageURL}&cp=${pagination.currentPage - 1}">&lt;</a></li>
					</c:if>
					
					
				
					<%-- 페이지 목록 --%>
					<c:forEach var="p" begin="${pagination.startPage}" end="${pagination.endPage}">
						
							<c:choose>
								<c:when test="${p == pagination.currentPage }">
									<li class="page-item active"><a class="page-link">${p}</a></li>
								</c:when>
								
								<c:otherwise>
									<li><a class="page-link" href="${pageURL}&cp=${p}">${p}</a></li>
								</c:otherwise>
							</c:choose>						
					</c:forEach>
					
					<%-- 현재 페이지가 마지막 페이지 미만인 경우 --%>
					<c:if test="${pagination.currentPage < pagination.maxPage }">
						<li><a class="page-link" href="${pageURL}&cp=${pagination.currentPage + 1}">&gt;</a></li>
					</c:if>
					
					<%-- 현재 페이지가 마지막 페이지가 아닌 경우 --%>
					<c:if test="${pagination.currentPage - pagination.maxPage + pagination.pageSize < 0}">
						<li><a class="page-link" href="${next}">&gt;&gt;</a></li>
					</c:if>

				</ul>
			</div>

		
		
		
		
			<!-- 검색창 -->
			<div class="my-5">
				<form action="#" method="GET" class="text-center" id="searchForm">
					<select name="sk" class="form-control" style="width: 100px; display: inline-block;">
						<option value="title">글제목</option>
						<option value="content">내용</option>
					</select>
					<input type="text" name="sv" class="form-control" style="width: 25%; display: inline-block;">
					<button class="form-control btn btn-primary" style="width: 100px; display: inline-block;">검색</button>
				</form>
			</div>
	</div>
	
	<jsp:include page="../../../views/common/footer.jsp" />
</body>
</html>