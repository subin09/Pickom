<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 댓글 작성</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- Bootstrap core JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <style>

		    
		}
 		div {
            border: 1px solid red;
        }

        div#mainlog,
        div#mainmypage {
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


        #normal-board-content {
            width: 100%;
            height: 400px;
            border: 1px solid black;
        }

        #comment-count {
            margin-left: 80%;
        }

        #contentlist-btn {
            margin-left: 85%;
        }

		.boardImg {
			width: 100px;
			height: 100px;
		}

        body {
            padding-top: 210px;
        }
        
        #contentImg0, 
        #contentImg1, 
        #contentImg2, 
        #contentImg3 {
        width : 100px;
        height: 100px;
        }
        
        
        /*댓글 */
        .commentWrite>table {	margin-top: 100px; }
		.cWriter {
			display: inline-block;
			vertical-align: top;
			font-size : 1.2em;
			font-weight: bold;
		}
		.cDate { display: inline-block; }
		.cContent, .commentBtnArea {
			height: 100%;
			width: 100%;
		}
		.commentBtnArea { text-align: right; }
		.commentUpdateContent {
			resize: none;
			width: 100%;
		}
		.comment-row{
			border-top : 1px solid #ccc;
			padding : 15px 0;
		}
       
    </style>


</head>

<body>
	
    <!-- header.jsp 동적 include -->
	<jsp:include page="../common/header.jsp" />




    <div class="container">
    
        
            <div> <h1>리뷰게시판</h1></div>
            

            
          	<!-- Category -->
			<h6 class="mt-4">카테고리 : [${board.categoryName }]</h6>
            
            <hr>                        
            <h2><span id="event-bod-title">${board.reviewTitle }</span></h2>
            
        	<!-- Date -->
			<p>
				<span class="board-dateArea">
					작성일 : <fmt:formatDate value="${board.createDt }" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
					<br>
					마지막 수정일 : <fmt:formatDate value="${board.modifyDt }" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
				</span>
		 		<span class="float-right">조회수 ${board.readCount } </span>
			</p>
			
			<div>
				<h1>${board.movieTitleEn }</h1>
			</div>
             <div>
	             <c:forEach items="${board.gnList }" var="gn">         
	             	<span>${gn.movieGenreNM}</span>
	             </c:forEach>
             </div>
     

            <!-- 회원이 글 쓴 내용 -->
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor"
                class="bi bi-person-circle" viewBox="0 0 16 16">
                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
                <path fill-rule="evenodd"
                    d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
            </svg>

            <!-- 댓글 단 횟수 count -->
            <i class="bi bi-person-circle">닉네임 : ${board.memberNickNm }</i>
            <i class="bi bi-chat" id="comment-count">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat"
                    viewBox="0 0 16 16">
                    <path
                        d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z" />
                </svg>

                <span>댓글 단 횟수</span>
            </i>



            <br>
            <br>
					<!-- 이미지 출력 -->	
					<c:forEach items="${board.atList }" var="at">
						<c:choose>
							<c:when test="${at.fileLevel == 0 && !empty at.fileName}">
								<c:set var="img0" value="${contextPath}/${at.filePath}${at.fileName}"/>
							</c:when>
							<c:when test="${at.fileLevel == 1 && !empty at.fileName}">
								<c:set var="img1" value="${contextPath}/${at.filePath}${at.fileName}"/>
							</c:when>
							<c:when test="${at.fileLevel == 2 && !empty at.fileName}">
								<c:set var="img2" value="${contextPath}/${at.filePath}${at.fileName}"/>
							</c:when>
							<c:when test="${at.fileLevel == 3 && !empty at.fileName}">
								<c:set var="img3" value="${contextPath}/${at.filePath}${at.fileName}"/>
							</c:when>
						</c:choose>
					</c:forEach>
				
					

	
					<div class="form-inline mb-2">
						<label class="input-group-addon mr-3 insert-label">업로드<br>이미지</label>
						<div class="mr-2 boardImg" id="contentImgArea0">
								
								<!-- img0 변수가 만들어진 경우 -->
								<c:if test="${!empty img0 }">
									<img id="contentImg0" src="${img0 }">
									
								</c:if>
						</div>
						<div class="mr-2 boardImg" id="contentImgArea1">
								<c:if test="${!empty img1 }">
									<img id="contentImg1" src="${img1 }">
								</c:if>
						</div>
	
						<div class="mr-2 boardImg" id="contentImgArea2">
								<c:if test="${!empty img2 }">
									<img id="contentImg2" src="${img2 }">
								</c:if>
						</div>
	
						<div class="mr-2 boardImg" id="contentImgArea3">
								<c:if test="${!empty img3 }">
									<img id="contentImg3" src="${img3 }">
								</c:if>
						</div>
					</div>	
					
            <!-- 글 쓴 내용  -->
            <div id="normal-board-contnent">
              ${board.reviewContent }
            </div>


            <hr>
			
			


			<div>
			
						
					<%-- 로그인된 회원과 해당 글 작성자가 같은 경우에만 버튼 노출--%>
					<c:if test="${loginMember.memberGrade == 'A'}">
						<button id="deleteBtn" class="btn btn-primary float-right mr-2">
							<a class="admin-a" href="${contextPath }/reviewBoard/list?type=200">신고글 목록으로</a>
						</button> 
						<button id="updateBtn" class="btn btn-primary float-right mr-2" onclick="recoverReport();">게시글 복구</button> 
						<button id="updateBtn" class="btn btn-primary float-right mr-2" onclick="deleteReport();">게시글 삭제</button>
					</c:if>
					
				

			</div>
		
		<form action="#" method="post" name="requestForm"></form>
			
		
		
		<div id="comment-area ">
				<!-- 댓글 작성 부분 -->
				<div class="commentWrite">
					<table align="center">
						<tr>
							<td id="commentContentArea">
								<textArea rows="3" id="commentContent"></textArea>
							</td>
							<td id="commentBtnArea">
								<button class="btn btn-primary" id="addComment" onclick="addComment();">
									댓글<br>등록
								</button>
							</td>
						</tr>
					</table>
				</div>
			
			
				<!-- 댓글 출력 부분 -->
				<div class="commentList mt-5 pt-2">
					<ul id="commentListArea">
						<c:forEach items="${cList}" var="comment">
							<li class="comment-row">
								<div>
									<p class="cWriter">${comment.memberNickNm}</p>
									<p class="cDate">작성일 : <fmt:formatDate value="${comment.commentDt }" pattern="yyyy년 MM월 dd일 HH:mm"/></p>
								</div>
				
								<p class="cContent">${comment.commentContent }</p>
								
								<c:if test="${!empty loginMember }">
									<c:if test="${comment.memberNo == loginMember.memberNo || comment.memberGrade == 'A'}">
										<div class="commentBtnArea">
											<button class="btn btn-primary btn-sm ml-1" id="updateComment" onclick="showUpdateComment(${comment.commentNo}, this)">수정</button>
											<button class="btn btn-primary btn-sm ml-1" id="deleteComment" onclick="deleteComment(${comment.commentNo})">삭제</button>
										
										</div>
									</c:if>
								
								</c:if>	
								
								
								
							</li>
						</c:forEach>
					</ul>
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
        
     

		function deleteReport(){
			
			swal({
				  title: "게시글을 삭제하시겠습니까?",
				  text: "삭제된 게시글은 복원 및 복구가 되지 않습니다.",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then(function(willDelete) { // <=== Only change is here
					if (willDelete) {
						  document.requestForm.action = "../reviewBoardDML/delete?type=201&no="+${board.reviewNo };
							// 현재 문서 내부에  name 속성값이 requestForm인 요소를 submit
							document.requestForm.submit();
					 
					  }
				});
			

		}
     
      	function recoverReport() {
      		swal({
				  title: "게시글을 복구하시겠습니까?",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then(function(willDelete) { // <=== Only change is here
					if (willDelete) {
						  document.requestForm.action = "../report/recoveryPost?type=201&no="+${board.reportNo };
							// 현재 문서 내부에  name 속성값이 requestForm인 요소를 submit
							document.requestForm.submit();
					 
					  }
				});
      	}
     
		
    </script>


</body>

</html>