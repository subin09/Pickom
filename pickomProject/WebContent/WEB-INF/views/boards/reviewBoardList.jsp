<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>고객센터</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- Bootstrap core JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>




    <style>
        div {
            /* border: 1px solid black;*/
        }

        div#mainlog,
        div#main {
            text-align: center;
        }

        #mainlog-div {
            margin-left: 10%;
        }

        #mainmypage-div {
            margin-right: 10%;
        }

        input#mainsearch {
            width: 500px;
        }

        #mainheader {
            background-color: beige;
        }

        #mainmenutitle {
            font-weight: bold;
            color: black;
            text-align: center;
            width: 10em;
        }

        #mainmenutitle:hover {
            color: orange;
        }

        #maincustomerservice {
            margin-left: 10%;
        }

        body {
            padding-top: 210px;
        }

        #content-area {
            width: 100%;
            margin: auto;
        }

        #content-area .button {
            display: flex;
            list-style: none;
            justify-content: flex-end;
            padding: 20px 0px 10px 0px;
        }

        #content-area .button input {
            border: 0;
            background-color: cadetblue;
            color: white;
            width: 115px;
            padding: 4px 8px;
            margin: 4px;
            border-radius: 4px;
            font-weight: 800px;

        }


        div.page_button {
            width: 100%;
            text-align: center;
        }

        div.page li {
            display: inline-block;
        }

        div .drop-btn {
            width: 120px;
            background-color: #ffffff;
            color: #151515;
            line-height: 32px;
            padding: 0 10px;
            font-size: 16px;
            font-weight: 500;
        }

        div .drop-btn i {
            float: right;
            line-height: 32px;
            font-size: 16px;
            cursor: pointer;
        }

        div .drop-btn li {
            color: #151515;
        }

        .button {
            text-align: right;

        }

        .input_search_area .input_component {
            text-align: center;
            /* background-color: red; */

        }

        #number_btn1 {
            text-align: center;
        }


        #diverse-button {
            height: 50px;
            /* background-color: blue; */
            margin: auto;
            display: inline-block;
        }

        #diverse-button>div {
            float: left;
            background-color: white;
            margin-right: 30px;

        }

        #reviewcontent td {
            cursor: pointer;
        }
        
        i::before {
        	content=" ";
        }
    </style>
</head>

<body>


     <!-- header.jsp 동적 include -->
	<jsp:include page="../common/header.jsp" />


    

        <div class="container">
            <h1>리뷰게시판</h1>
            <div id="content-area">
                <c:if test="${ !empty categoryList}"> 
					<div class="mb-2">
						<label>카테고리</label> 
						<select	id="categoryCode" name="categoryCode" style="width: 150px;" onchange="if(this.value) location.href=(this.value);">
										<option selected>장르별</option>		
										<option value="${contextPath}/reviewBoard/list?type=0&cp=1" name="list">전체</option>		
									<c:forEach items="${categoryList}" var="c">
										<option value="${contextPath}/reviewBoard/list?type=${c.movieGenreCode }&cp=1" name="${c.movieGenreCode }">${c.movieGenreNM }</option>
									</c:forEach>
								
						</select>
					</div>
			</c:if>
                <div id="reviewcontent">
                    <table class="table table-hover">
                        <thead>
                            <tr>

                                <th scope="col">No</th>
                                <th scope="col">카테고리</th>
                                <th scope="col">제목</th>
                                <th scope="col">작성자</th>
                                <th scope="col">날짜</th>
                                <th scope="col">조회</th>
                            </tr>
                        </thead>
                      
                        <%-- 게시글 목록 출력 --%>
						<tbody>
						
							
							<%-- 공지사항  if else 문--%>
							<%-- if else 문 --%>
							<c:choose> 
								
								<%-- 조회된 게시글 목록이 없는 경우 --%>
								<c:when test="${empty noticeList }">
									<tr>
										<td colspan="6">공지사항이 존재하지 않습니다</td>
									</tr>
									
								</c:when>
								<%-- 조회된 게시글 목록이 있는 경우 --%>
								<c:otherwise>
									<c:forEach items="${noticeList }" var="notice" varStatus="b">
										<tr class="notice">
											<%-- 글번호 --%>
											<th scope="row">공지</th>
											<%-- 카테고리 --%>
											<td>알림/결과</td>
											
											<%-- 글 제목 --%>
											<td class="boardTitle">
												<a href="view?type=${pagination.boardType }&no=${notice.reviewNo }&cp=${pagination.currentPage}">
													${notice.reviewTitle}
												</a>
											</td>
											
											
											<%-- 작성자 --%>
											<td>${notice.memberNickNm }</td>
											<%-- 작성일 --%>
											<td>
												
												<fmt:formatDate var="createDate" value="${notice.createDt}"  pattern="yyyy-MM-dd"/>                          
												<fmt:formatDate var="today" value="<%= new java.util.Date() %>"  pattern="yyyy-MM-dd"/>                          
												
												<c:choose>
													<%-- 글 작성일이 오늘이 아닐 경우 --%>
													<c:when test="${createDate != today}">
														${createDate}
													</c:when>
													
													<%-- 글 작성일이 오늘일 경우 --%>
													<c:otherwise>
														<fmt:formatDate value="${notice.createDt}"  pattern="HH:mm"/>                          
													</c:otherwise>
												</c:choose>
												
											</td>
											<%-- 조회수 --%>
											<td>${notice.readCount }</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							
							
							
							
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
											<%-- 글번호 --%>
											<th scope="row">${board.reviewNo }</th>
											<%-- 카테고리 --%>
											<td>${board.movieTitleKo}</td>
											
											<%-- 검색창일 때 아닐 때 구분 --%>
											<c:choose>
												<c:when test="${ !empty searchType}">
													<c:set var="pageURL" value="view?type=${pagination.boardType }&searchValue=${searchValue }&searchType=${searchType }"></c:set>
												
													<td class="boardTitle">
														<a href="${pageURL }&no=${board.reviewNo }&cp=${pagination.currentPage}">
															${board.reviewTitle }
														</a>
													</td>
												</c:when>
												
												<c:otherwise>
													<td class="boardTitle">
														<a href="view?type=${pagination.boardType }&no=${board.reviewNo }&cp=${pagination.currentPage}">
															${board.reviewTitle }
														</a>
													</td>
												</c:otherwise>
											</c:choose>
										
											
											
											<%-- 작성자 --%>
											<td>${board.memberNickNm }</td>
											<%-- 작성일 --%>
											<td>
												
												<fmt:formatDate var="createDate" value="${board.createDt}"  pattern="yyyy-MM-dd"/>                          
												<fmt:formatDate var="today" value="<%= new java.util.Date() %>"  pattern="yyyy-MM-dd"/>                          
												
												<c:choose>
													<%-- 글 작성일이 오늘이 아닐 경우 --%>
													<c:when test="${createDate != today}">
														${createDate}
													</c:when>
													
													<%-- 글 작성일이 오늘일 경우 --%>
													<c:otherwise>
														<fmt:formatDate value="${board.createDt}"  pattern="HH:mm"/>                          
													</c:otherwise>
												</c:choose>
												
											</td>
											<%-- 조회수 --%>
											<td>${board.readCount }</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
	
							
							
						
						</tbody>
                    </table>
		

                    <div id="number_btn1">
                        <div class="page">
										<%-- 로그인 되어 있을 경우에만 글쓰기 버튼을 노출 --%>
								<c:if test="${!empty loginMember }">
									<button type="button" class="btn btn-primary float-right" id="insertBtn"
									 onclick="location.href='../reviewBoardDML/insertForm?type=${pagination.boardType }';">글쓰기</button>
								 </c:if>	
								
								
								
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
                           
                        </div>

	
	
						<%-- 조건검색 창 --%>
                        <form action="${contextPath}/reviewBoard/list?type=100&cp=1" method="post" role="form" onsubmit="return searchValidate();">
								<div class="mb-2">
									<label class="input-group-addon mr-3 insert-label">조건검색</label> 
									<select	class="custom-select" id="searchType" name="searchType" style="width: 150px;">
											<option value="1">영화 제목</option>
											<option value="2">게시글 제목</option>
											<option value="3"">게시글 내용</option>
									</select>
								</div>

                                <div id="">
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" placeholder="Recipient's username" name="searchValue"
                                            aria-label="Recipient's username" aria-describedby="button-addon2" id="searchInput">
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-secondary" type="submit"
                                                id="button-addon2">submit</button>
                                        </div>
                                    </div>
                                </div>

                            
                        </form>
                    </div>






                </div>
            </div>
           </div>
            <!-- footer.jsp 동적 include -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
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

            <script>
                var date = new Date();
                var year = date.getFullYear(date);
                $(document).ready(function () {
                    $("#currentYear").text(year);
                });

                $("#reviewcontent td").on("click", function () {

                    const boardNo = $(this).parent().children().eq(0).text();

                    location.href = "#?boardNo=" + boardNo;
                });
					
             // 유효성 검사 
        		function boardValidate() {
        			if($("#searchInput").val().trim().length == 0) {
        				alert("검색조건을 입력해주세요.");
        				$("#title").focus();
        				return false;

        			}
        	
        		}

        		// 이미지 영역을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
        		$(function() {
        			$(".boardImg").on("click", function() {
        				var index = $(".boardImg").index(this);
        				$("#img" + index).click();
        			});

        		});
             
            </script>



</body>

</html>