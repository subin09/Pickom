<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 상세</title>
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
			width: 200px;
			height: 200px;
		}

        
        
        #contentImg0, 
        #contentImg1, 
        #contentImg2, 
        #contentImg3 {
        width : 200px;
        height: 200px;
        }
        
        .noticeBoard_container h1 {
       		margin: 70px 0px;
       }
        
       .noticeBoard_container p {
       	margin: 4rem 0;
       }
       
       	.noticeBoard_container2 {
       	margin-bottom: 100px;
       }
       
       #normal-board-contnent {
       margin: 40px 0;
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



    <div class="container noticeBoard_container shadow p-3 mb-5 bg-white rounded">
    
        <main class="noticeBoard_container2">
            <div> <h1>공지사항</h1></div>
            

            
            <hr>                        
            <h2><span id="event-bod-title">${board.faqTitle }</span></h2>
            
        	<!-- Date -->
			<p>
				<span class="board-dateArea">
					작성일 : <fmt:formatDate value="${board.faqDate }" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
					<br>
					마지막 수정일 : <fmt:formatDate value="${board.faqModifyDt }" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
				</span>
			</p>
             
                      
     

            <!-- 회원이 글 쓴 내용 -->
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor"
                class="bi bi-person-circle" viewBox="0 0 16 16">
                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
                <path fill-rule="evenodd"
                    d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
            </svg>

            <!-- 댓글 단 횟수 count -->
            <i class="bi bi-person-circle">닉네임 : ${board.memberNickNm }</i>
           


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
            <div id="normal-board-contnent">
              ${board.faqContent }
            </div>


            

            


            <br>






	<%-- 로그인된 회원과 해당 글 작성자가 같은 경우에만 버튼 노출--%>
	<c:if test="${loginMember.memberGrade == 'A'}">
		<button id="deleteBtn" class="btn btn-primary float-right mr-2">삭제</button> 
		<button id="updateBtn" class="btn btn-primary float-right mr-2" onclick="fnRequest('updateForm')";>수정</button> 
	</c:if>
	
		<a href="list?&cp=${param.cp}" class="btn btn-primary float-right mr-2">목록으로</a>
           
        </main>
    </div>
	
	
	
					
	


	<form action="#" method="post" name="requestForm">
		<input type="hidden" value="${board.faqNo }" name="boardNo">
		<input type="hidden" value="${param.cp }" name="cp">
	
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
		function fnRequest(addr) {

			document.requestForm.action = "../FAQBoardDML/" + addr;
			
			// 현재 문서 내부에  name 속성값이 requestForm인 요소를 submit
			document.requestForm.submit();

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