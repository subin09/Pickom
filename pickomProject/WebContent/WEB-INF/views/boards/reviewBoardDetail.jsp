<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰게시판 상세</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">


<!-- 라이트 박스 시작!  -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.1/css/lightbox.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.1/js/lightbox.min.js"></script>
<!-- 라이트 박스 끝! -->


<!-- Bootstrap core JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <style>

		    
	

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


        
        .reportBtn {
        	color: white;
       	text-decoration: none;
        	}
       .reportBtn:hover{
       	text-decoration: none;
       	color: white;
       }
        
        #contentImg0, 
        #contentImg1, 
        #contentImg2, 
        #contentImg3 {
        width : 100px;
        height: 100px;
        }
        
        
        /*댓글 */
        .commentWrite>table {	margin-top: 10px; }
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
		.complainDetail__main h1 {
       		margin: 70px 0px;
       }
       
       .reportBtn2{
       	text-decoration: none;
       	color: white;
       }
       
       .reportBtn2:hover{
       	text-decoration: none;
       	color: white;
       	font-weight: bold;
       }
       
       .title-area{
       	margin-bottom: 2rem;
       	height: 200px;
       }
    </style>


</head>

<body>
	
    <!-- header.jsp 동적 include -->
	<jsp:include page="../common/header.jsp" />




    <div class="container complainDetail__main">
    
        
            <div> <h1>리뷰게시판</h1></div>
            

            
            
          	
			
			
			
            
            <hr>                        
            <h2><span id="event-bod-title">제목 : ${board.reviewTitle }</span></h2>
            <hr>                        
            
        	<!-- Date -->
			<p>
				<span class="board-dateArea">
					작성일 : <fmt:formatDate value="${board.createDt }" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
					<br>
					마지막 수정일 : <fmt:formatDate value="${board.modifyDt }" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
				</span>
		 		<span class="float-right">조회수 ${board.readCount } </span>
			</p>
			
            <hr>                        

			<div>
				<h1>${board.movieTitleEn }</h1>
			</div>
			
             <div>
             <label>영화 장르 :</label>
	             <c:forEach items="${board.gnList }" var="gn">         
	             <span> [${gn.movieGenreNM}]</span>
	             </c:forEach>
             </div>
     
				<br>
				
            <!-- 회원이 글 쓴 내용 -->
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor"
                class="bi bi-person-circle" viewBox="0 0 16 16">
                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
                <path fill-rule="evenodd"
                    d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
            </svg>
            <!-- 댓글 단 횟수 count -->
            <i class="bi bi-person-circle">닉네임 : ${board.memberNickNm }</i>

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
				
					

	
					<div class="form-inline mb-2 title-area">
						<label class="input-group-addon mr-3 insert-label">업로드<br>이미지</label>
						<div class="mr-2 boardImg" id="contentImgArea0">
								
								<!-- img0 변수가 만들어진 경우 -->
								<c:if test="${!empty img0 }">
								<a href="${img0}" data-lightbox="example-set">
									<img id="contentImg0" src="${img0 }">
								</a>
								</c:if>
						</div>
						<div class="mr-2 boardImg" id="contentImgArea1">
								<c:if test="${!empty img1 }">
								<a href="${img1}" data-lightbox="example-set">
									<img id="contentImg1" src="${img1 }">
								</a>
								</c:if>
						</div>
	
						<div class="mr-2 boardImg" id="contentImgArea2">
								<c:if test="${!empty img2 }">
								<a href="${img2}" data-lightbox="example-set">
									<img id="contentImg2" src="${img2 }">
								</a>
								</c:if>
						</div>
	
						<div class="mr-2 boardImg" id="contentImgArea3">
								<c:if test="${!empty img3 }">
								<a href="${img3}" data-lightbox="example-set">
									<img id="contentImg3" src="${img3 }">
								</a>
								</c:if>
						</div>
					</div>	
					
            <!-- 글 쓴 내용  -->
            <div id="normal-board-contnent" style="border: 1px solid black; width: 100%;  height: 400px;">
              ${board.reviewContent }
            </div>


			
			


			<div>
				<c:if test="${!empty loginMember }">
						
					<%-- 로그인된 회원과 해당 글 작성자가 같은 경우에만 버튼 노출--%>
					<c:if test="${loginMember.memberGrade == 'A' || loginMember.memberNo == board.memberNo}">
						<button id="deleteBtn" class="btn btn-primary float-right mr-2" onclick="deleteRequest();">삭제</button> 
						<button id="updateBtn" class="btn btn-primary float-right mr-2" onclick="updateRequest('updateForm');">수정</button> 
					</c:if>
					
					<%-- 로그인된 회원과 해당 글 작성자가 다른 경우에만 신고버튼 노출--%>
					<c:if test="${loginMember.memberNo != board.memberNo && board.memberGrade != 'A' && loginMember.memberGrade != 'A'}">
								<button id="deleteBtn" class="btn btn-primary float-right mr-2">
									<a class="reportBtn" href="${contextPath }/report/reportPostForm?type=0&no=${board.reviewNo}">신고</a>
								</button> 
							
					</c:if>
					
				</c:if>	
				
				<a href="list?type=${boardType }&cp=${cp}" class="btn btn-primary float-right mr-2">목록으로</a>
			</div>
		
			<br>
			<br>
            <hr>
			
		
		
		<div id="comment-area ">
				<!-- 댓글 작성 부분 -->
				<div class="commentWrite">
					<table align="center">
						<tr>
							<td id="commentContentArea">
								<textArea rows="3" id="commentContent" style = "width:600px; resize: none;"></textArea>
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
			
		</div>	
			
			
			
			
	<form action="#" method="post" name="requestForm">
			<input type="hidden" value="${board.reviewNo }" name="boardNo">
			<input type="hidden" value="${param.cp }" name="cp">
			<input type="hidden" value="${param.type }" name="type">
			
	</form>

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
        
     // 현재 문서 내부에  name 속성값이 requestForm인 요소의 action속성값을 변경
		function updateRequest(addr) {
			const boardtype = ${boardType};

			document.requestForm.action = "../reviewBoardDML/" +addr;
			// 현재 문서 내부에  name 속성값이 requestForm인 요소를 submit
			document.requestForm.submit();

		}
     
		
		// 게시글 삭제 
		function deleteRequest(){
			
			swal({
				  title: "게시글을 삭제하시겠습니까?",
				  text: "삭제된 게시글은 복원 및 복구가 되지 않습니다.",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then(function(willDelete) { // <=== Only change is here
					if (willDelete) {
						  document.requestForm.action = "../reviewBoardDML/delete?no="+${board.reviewNo };
							// 현재 문서 내부에  name 속성값이 requestForm인 요소를 submit
							document.requestForm.submit();
					 
					  }
				});
			

		}
     
     
     
		// 로그인한 회원의 회원 번호, 비로그인 시 "" (빈문자열)
		// const loginMemberNo = ${loginMember.memberNo}; 이렇게 썼는데 로그인 안 한 상태면 
		// const loginMemberNo = ; 이렇게 나와서 synctaxError 나옴
		const loginMemberNo = "${loginMember.memberNo}";
		const loginMemberGrade = "${loginMember.memberGrade}";

		// 현재 게시글 번호
		const boardNo = ${board.reviewNo};

		// 수정 전 댓글 요소를 저장할 변수 (댓글 수정 시 사용)
		let beforeCommentRow;



		//-----------------------------------------------------------------------------------------
		// 댓글 등록}
		// 등록 눌러도 깜빡거리면서 넘어가는 거 없이 바로 보여지게 하겠다 
		function addComment()	{ // 등록버튼에 onclick으로 연결되어있는 함수
			
			// 작성된 댓글 내용 얻어오기
			const commentContent = $("#commentContent").val();
			
			// 로그인 안 되어있으면 댓글 못 쓰니까 로그인 안되어있을 때 
			if(loginMemberNo == ""){
				swal("로그인 후 이용해주세요.");
			} else {
				if(commentContent.trim() == ""){ // 작성된 댓글이 없을 경우
					swal("댓글 작성 후 클릭해주세요.");
				} else {
					
					
					// 로그인 O, 댓글 작성 O
					$.ajax({
						url : "${contextPath}/reviewComment/insertComment", // 필수속성!!!!
						type : "POST", 
						data : {"memberNo" : loginMemberNo, // Controller에서 getParameter로 받을 애들
								"boardNo" : boardNo, 
								"commentContent" : commentContent }, 
						success : function(result){
							if(result > 0){ //  댓글 삽입 성공
								swal({"icon" : "success" , "title" : "댓글 등록 성공"});
								$("#commentContent").val(""); // 댓글 작성란에 썼던 내용 삭제 
								selectCommentList(); // 비동기로 댓글 목록 갱신 
								console.log(boardNo);
							}					
						}, 
						error : function(){
							console.log("댓글 삽입 실패");
						}
					
					}); // 여기까지만 하면 새로고침 해야지만 등록한 댓글이 보임
						// DB가서 싹 다 다시 가져와야 함
				}
			}
			
		}	
			


		//-----------------------------------------------------------------------------------------
		//해당 게시글 댓글 목록 조회
		function selectCommentList(){
		 	$.ajax({
		 		url : "${contextPath}/reviewComment/list", 
		 		data : {"boardNo" : boardNo}, 
		 		type : "POST", 
		 		dataType : "JSON", // 응답되는 데이터의 형식이 JSON임을 알려줌 -> 자바스크립트 객체로 변환됨
		 		success : function(cList){
		 			  $("#commentListArea").html(""); // 기존 정보 초기화
		 	         
		 	         $.each(cList, function(index, item){
		 	        	 
		 	            // $.each() : jQuery의 반복문 
		 	            // rList : ajax 결과로 받은 댓글이 담겨있는 객체 배열 
		 	            // index : 순차 접근 시 현재 인덱스 
		 	            // item : 순차 접근 시 현재 접근한 배열 요소(댓글 객체 하나)
		 	            
		 	            var li = $("<li>").addClass("comment-row");
		 	         
		 	            // 작성자, 작성일, 수정일 영역 
		 	            var div = $("<div>");
		 	            var cWriter = $("<p>").addClass("cWriter").text(item.memberNickNm);
		 	            var cDate = $("<p>").addClass("cDate").text("작성일 : " + item.commentDt);
		 	            div.append(cWriter).append(cDate)
		 	            
		 	            
		 	            
		 	            
		 	            // 대댓글, 수정, 삭제 버튼 영역
		 	            var commentBtnArea = $("<div>").addClass("commentBtnArea");
		 	            
		 	            // 현재 댓글의 작성자와 로그인한 멤버의 아이디가 같을 때 버튼 추가
		 	           
		 	            if(loginMemberNo != ""){
		 	            	
		 	            	
				 	            if(item.memberNo == loginMemberNo && loginMemberGrade != 'A'){ // 일반회원인데 본인일 때
				 	               // 신고댓글 아닐 때ㄴ
					 	              if(item.reportNo != ""){ // 신고댓글일 때
					 	            	 var cContent = $("<p>").addClass("cContent").html("작성하신 댓글이 신고되었습니다. 관리자의 복구승인이 필요합니다.");
					 	              } else {
						 	               // ** 추가되는 댓글에 onclick 이벤트를 부여하여 버튼 클릭 시 수정, 삭제를 수행할 수 있는 함수를 이벤트 핸들러로 추가함. 
						 	               var showUpdate = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("수정").attr("onclick", "showUpdateComment("+item.commentNo+", this)");
						 	               var deleteComment = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("삭제").attr("onclick", "deleteComment("+item.commentNo+")");
						 	               
						 	               commentBtnArea.append(showUpdate).append(deleteComment);
						 	              var cContent = $("<p>").addClass("cContent").html(item.commentContent);
					 	              }
				 	              }
				 	            
								if(item.memberNo != loginMemberNo && loginMemberGrade != 'A'){ //  일반회원인데 본인이 아닐 때
				 	               
				 	             	 if(item.reportNo != ""){ // 신고댓글일 때
					 	            	 var cContent = $("<p>").addClass("cContent").html("신고된 댓글입니다.");
					 	            } else{
					 	               // ** 추가되는 댓글에 onclick 이벤트를 부여하여 버튼 클릭 시 수정, 삭제를 수행할 수 있는 함수를 이벤트 핸들러로 추가함. 
					 	               var reportBtn = $("<button>").addClass("btn btn-primary btn-sm ml-1");
					 	               var reportLink = $("<a>").text("신고").attr("href", "${contextPath }/report/reportCmForm?type=0&no="+item.reviewNo+"&cno="+item.commentNo).addClass("reportBtn2");
					 	               
					 	               reportBtn.append(reportLink);
					 	               commentBtnArea.append(reportBtn);
					 	               
					 	              var cContent = $("<p>").addClass("cContent").html(item.commentContent);
					 	            	
					 	            }
				 	            }
								
								if(loginMemberGrade == 'A'){ // 관리자일 때 
									if(item.reportNo != ""){ // 신고댓글일 때
										   var recoverBtn = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("복구").attr("onclick", "recoverComment("+item.reportNo+")");
						 	               var showUpdate = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("수정").attr("onclick", "showUpdateComment("+item.commentNo+", this)");
						 	               var deleteComment = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("삭제").attr("onclick", "deleteComment("+item.commentNo+")");
						 	              
						 	              var cContent = $("<p>").addClass("cContent").html(item.commentContent).css({color: "red", fontWeight : "bold"});
						 	              commentBtnArea.append(showUpdate).append(deleteComment).append(recoverBtn);
									} else { // 신고댓글이 아닌 일반 댓글일 때
											var showUpdate = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("수정").attr("onclick", "showUpdateComment("+item.commentNo+", this)");
						 	               var deleteComment = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("삭제").attr("onclick", "deleteComment("+item.commentNo+")");
						 	               
						 	              var cContent = $("<p>").addClass("cContent").html(item.commentContent);
						 	               commentBtnArea.append(showUpdate).append(deleteComment);
									}
								} 
								
			 				
		 	            } else { // 비로그인 상태일 때
			 	           var cContent = $("<p>").addClass("cContent").html(item.commentContent);
			 	          if(item.reportNo != ""){ // 신고댓글일 때
			 	            	 var cContent = $("<p>").addClass("cContent").html("신고된 댓글입니다.");
			 	            }
		 	            }
		 	            
		 	          
		 	            
		 	            
		 	            // 댓글 요소 하나로 합치기
		 	            li.append(div).append(cContent).append(commentBtnArea);
		 	            
		 	            
		 	            // 합쳐진 댓글을 화면에 배치
		 	            $("#commentListArea").append(li);
		 	         });
		 			
		 		}, 
		 		error : function(){
		 			console.log("댓글 목록 조회 실패");
		 		}
		 	})
		}

		// -----------------------------------------------------------------------------------------
		// 일정 시간마다 댓글 목록 갱신
		//const replyInterval = setInterval(function(){
			selectCommentList();
		//}, 5000);


		// -----------------------------------------------------------------------------------------
		// 댓글 수정 폼

		function showUpdateComment(commentNo, el){
			// el : 수정 버튼 클릭 이벤트가 발생한 요소
			
			// 이미 열려있는 댓글 수정 창이 있을 경우 닫아주기
			if($(".commentUpdateContent").length > 0){
				$(".commentUpdateContent").eq(0).parent().html(beforeCommentRow);
			}
				
			// 댓글 수정화면 출력 전 요소를 저장해둠.
			beforeCommentRow = $(el).parent().parent().html();
			
			// 작성되어있던 내용(수정 전 댓글 내용) 
			var beforeContent = $(el).parent().prev().html();

			
			// 이전 댓글 내용의 크로스사이트 스크립트 처리 해제, 개행문자 변경
			// -> 자바스크립트에는 replaceAll() 메소드가 없으므로 정규 표현식을 이용하여 변경
			beforeContent = beforeContent.replace(/&amp;/g, "&");	
			beforeContent = beforeContent.replace(/&lt;/g, "<");	
			beforeContent = beforeContent.replace(/&gt;/g, ">");	
			beforeContent = beforeContent.replace(/&quot;/g, "\"");	
			
			beforeContent = beforeContent.replace(/<br>/g, "\n");	
			
			
			// 기존 댓글 영역을 삭제하고 textarea를 추가 
			$(el).parent().prev().remove();
			var textarea = $("<textarea>").addClass("commentUpdateContent").attr("rows", "3").val(beforeContent);
			$(el).parent().before(textarea);
			
			
			// 수정 버튼
			var updateComment = $("<button>").addClass("btn btn-primary btn-sm ml-1 mb-4").text("댓글 수정").attr("onclick", "updateComment(" + commentNo + ", this)");
			
			// 취소 버튼
			var cancelBtn = $("<button>").addClass("btn btn-primary btn-sm ml-1 mb-4").text("취소").attr("onclick", "updateCancel(this)");
			
			var commentBtnArea = $(el).parent();
			
			$(commentBtnArea).empty(); 
			$(commentBtnArea).append(updateComment); 
			$(commentBtnArea).append(cancelBtn);


			
		}

		//-----------------------------------------------------------------------------------------
		//댓글 수정 취소 시 원래대로 돌아가기
		function updateCancel(el){
			$(el).parent().parent().html(beforeCommentRow);
		}


		//-----------------------------------------------------------------------------------------
		// 댓글 수정
		function updateComment(commentNo, el){

			// 수정된 댓글 내용
			const commentContent = $(el).parent().prev().val();
			
			$.ajax({
				url : "${contextPath}/reviewComment/updateComment",
				type : "POST", 
				data : {"commentNo" : commentNo,
						"commentContent" : commentContent},
				success : function(result){
					if(result > 0){
						swal({"icon" : "success" , "title" : "댓글 수정 성공"});
						selectCommentList();
					}
				},
				error : function(){
					console.log("댓글 수정 실패");
				}
				
			})
		}


		//-----------------------------------------------------------------------------------------
		//댓글 삭제
		function deleteComment(commentNo){
		
			
			swal({
				  title: "댓글을 삭제하시겠습니까?",
				  text: "삭제된 댓글은 복원 및 복구가 되지 않습니다.",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then(function(willDelete) { // <=== Only change is here
					if (willDelete) {
						var url = "${contextPath}/reviewComment/deleteComment";
						
						$.ajax({
							url : url,
							data : {"commentNo" : commentNo},
							success : function(result){
								if(result > 0){
									selectCommentList(boardNo);
									
									swal({"icon" : "success" , "title" : "댓글 삭제 성공"});
								}
								
							}, error : function(){
								console.log("ajax 통신 실패");
							}
						});
					  }
				});

		}
		
		function recoverComment(commentNo){
			swal({
				  title: "댓글을 복구하시겠습니까?",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then(function(willDelete) { // <=== Only change is here
					if (willDelete) {
						var url = "${contextPath}/report/recoveryComment";
						
						$.ajax({
							url : url,
							data : {"no" : commentNo},
							success : function(result){
								if(result > 0){
									selectCommentList(boardNo);
									
									swal({"icon" : "success" , "title" : "댓글 복구 성공"});
								}
								
							}, error : function(){
								console.log("ajax 통신 실패");
							}
						});
					  }
				});
		}
		
		
		lightbox.option({
		    resizeDuration: 200,
		    wrapAround: true,
		    disableScrolling: false,
		    fitImagesInViewport:false
		})
    </script>


</body>

</html>