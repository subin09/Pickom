<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title></title>
<link rel="stylesheet" type="text/css" href="css/01_header.css">
<link rel="stylesheet" type="text/css" href="css/02_footer.css">



<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- Bootstrap core JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>





<style>
/*         div {
            border: 1px solid red;
        } */
#paging-num {
	width: 190px;
	height: 50px;
	text-align: center;
	margin: auto;
}

#write-btn-area {
	width: 80px;
	height: 40px;
	text-align: center;
	margin-left: 91%;
}

#event-bod-list-title {
	width: 210px;
	height: 50px;
}

body {
	padding-top: 210px;
}

/* style="background: linear-gradient(40deg,#FCA5F1,#B5FFFF)" */
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>


	<div class="container">


		<div id="event-bod-list-title">
			<h1>행사게시판</h1>
		</div>
		<hr>


		<div id="board-list-area">
			<table class="table table-hover table-striped my-5"
				id="event-list-table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">NO</th>
						<th scope="col">제목</th>
						<th scope="col">작성자(닉네임)</th>
						<th scope="col">카테고리</th>
						<th scope="col">작성일</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>

				<%-- 게시글 목록 출력 --%>
				<tbody style="background: white; ">

					<c:choose>

						<%-- 조회된 게시글 목록이 없는 경우 --%>
						<c:when test="${empty boardList}">
							<tr>
								<td colspan="6">게시글이 존재하지 않습니다.</td>
							</tr>
						</c:when>

						<%-- 조회된 게시글 목록이 있는 경우 --%>
						<c:otherwise>

							<c:forEach items="${boardList}" var="board">

								<tr>
                                 
									<%-- 게시판 번호 --%>
									<td>${board.eventBodNo}</td>
									<%-- 게시판 제목 --%>
									<td class ="boardTitle">
										<a href="view?no=${board.eventBodNo}&cp=${pagination.currentPage}" id="boardTitle">
										${board.eventBodTitle}
										</a>
									</td>
									<%-- 작성자(닉네임) --%>
									<td>${board.memberNm}</td>
									<%-- 게시판 카테고리 --%>
									<td>${board.eventCategoryNm}</td>
									<%-- 게시판 작성일 --%>
									<td>${board.eventBodCreateDt}</td>
									<%-- 게시판 조회수 --%>
									<td>${board.eventBodReadCount}</td>

								</tr>

							</c:forEach>


						</c:otherwise>

					</c:choose>

				</tbody>
			</table>
		</div>




	<c:if test="${!empty loginMember && loginMember.memberNo == 1}">
		<div id="write-btn-area">
			<a href="../eventBoard2/insertForm" class="btn btn-primary">글쓰기</a>
		</div>
	</c:if>


		<%---------------------- Pagination start----------------------%>

		<c:set var="pageURL" value="list?" />
		<c:set var="prev" value="cp=${pagination.prevPage}" />
		<c:set var="next" value="cp=${pagination.nextPage}" />


		<div class="my-5" style="width:500px; margin-left: 50%">
			<ul class="pagination">

				<c:if test="${pagination.currentPage > 10}">
					<a class="page-link" href="${pageURL}&${prev}">&lt;&lt;</a>
				</c:if>

				<%-- 현재 페이지가 2페이지 초과인 경우--%>
				<c:if test="${pagination.currentPage >= 2 }">
					<li><a class="page-link"
						href="${pageURL}&cp=${pagination.currentPage -1}">&lt;</a></li>
				</c:if>



				<%-- 페이지 목록--%>
				<c:forEach var="p" begin="${pagination.startPage}"
					end="${pagination.endPage}">
					<li><c:choose>

							<c:when test="${p == pagination.currentPage}">
								<li class="page-item active"><a class="page-link">${p}</a></li>
							</c:when>


							<c:otherwise>
								<li><a class="page-link" href="${pageURL}&cp=${p}">${p}</a></li>
							</c:otherwise>

						</c:choose></li>

				</c:forEach>



				<%-- 현재 페이지가 마지막 페이지 미만인 경우--%>
				<c:if test="${pagination.currentPage < pagination.maxPage }">
					<li><a class="page-link"
						href="${pageURL}&cp=${pagination.currentPage +1}">&gt;</a></li>
				</c:if>

				<%-- 현재 페이지가 마지막 페이지 미만인 경우--%>
				<c:if
					test="${pagination.currentPage - pagination.maxPage + pagination.pageSize <0 }">
					<li><a class="page-link" href="${pageURL}&${next}">&gt;&gt;</a></li>
				</c:if>


			</ul>
		</div>



		<%---------------------- Pagination end----------------------%>



		<!-- 검색창 -->
		<div class="my-5">
			<form action="list" method="GET" class="text-center" id="searchForm">

			<!-- 게시판 타입 유지를 위한 태그  -->
			
				<select name="sk" class="form-control"
					style="width: 100px; display: inline-block;">
					<option value="title">글제목</option>
					<option value="content">내용</option>
					<option value="titcont">제목+내용</option>
				</select> <input type="text" name="sv" class="form-control"
					style="width: 25%; display: inline-block;">
				<button class="form-control btn btn-primary"
					style="width: 100px; display: inline-block;">검색</button>
			</form>
		</div>










	</div>










	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	
	<script>
	
	const loginMemberNo = "${loginMember.memberNo}";
	
	
	
	
	(function(){
		var searchKey = "{param.sk}";
		var searchValue = "${param.sv}";
		
		$("select[name=sk] > option").each(function(index,item){
			
			if($(item).val()==searchKey){
				$(item).prop("selected", true);
			}
		});
		
		$("input[name=sv]").val(searchValue);
	})();
	
	
	
	
	
	</script>

</body>
</html>