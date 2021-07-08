<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>

<link rel="shortcut icon" href="#">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상세 게시글</title>



<!-- 라이트 박스 시작!  -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.1/css/lightbox.min.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.1/js/lightbox.min.js"></script>
<!-- 라이트 박스 끝! -->


<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>



<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">

<!-- Bootstrap core JS -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>

<!-- sweetalert API 추가 -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>









<style>
/* div {
	border: 1px solid red;
} */

 

#content-box {
	width: 100%;
	height: 400px;
	border: 1px solid black;
}

#comment-count {
	margin-left: 75%;
}

#contentlist-btn {
	margin-left: 85%;
}



.form-inline{
display: flex;

}

.title-area{
height: 200px;
}


.title-area div {
    width: 200px;
    height: 200px;
}


#contentImg0, #contentImg1, #contentImg2, #contentImg3 {
	width: 200px;
	height: 200px;
	
}




.boardImg {
	width: 200px;
	height: 200px;
	position: relative;
}

.boardImg > a:hover img {
  filter: brightness(70%);
}

#explain{ 
position: absolute;
visibility: hidden;
top : 50%;
left : 20%;
font-weight: bold;
font-size: 15px;
color: white;
}
.boardImg:hover #explain{
visibility: visible;
}



</style>


</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>




	<div class="container shadow p-3 mb-5 bg-white rounded">


		<h1 style="margin-top: 70px;">행사게시판</h1>
		<div class=text-right>
			<a href="list?cp=${param.cp}" class="btn btn-primary">목록</a>
		</div>



		<!-- Category -->
		<h6 class="mt-4">카테고리 : [${board.eventCategoryNm }]</h6>

		<hr>

		<!-- Date -->
		<p>
			<span class="board-dateArea"> 작성일 : <fmt:formatDate
					value="${board.eventBodCreateDt}" pattern="yyyy년 MM월 dd일 HH:mm:ss" />
				<br> 마지막 수정일 : <fmt:formatDate
					value="${board.eventBodModifyDt}" pattern="yyyy년 MM월 dd일 HH:mm:ss" />
			</span> <span class="float-right">조회수 ${board.eventBodReadCount } </span>
		</p>

		<hr>
		<h2>제목 : ${board.eventBodTitle}</h2>
		<hr>
		<span>시작일 : [${board.startDate}]</span>
		<label>~~~</label>
		<span>종료일 : [${board.finalDate}]</span> <br> <br>

		<!-- 회원이 글 쓴 내용 -->
		<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30"
			fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
                <path fill-rule="evenodd"
				d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
            </svg>


		<i class="bi bi-person-circle">닉네임 : ${board.memberNm }</i>

<br>
<br>





		<!-- 이미지 출력 -->
		<c:forEach items="${board.atList}" var="at">
			<c:choose>
				<c:when test="${at.eventFileLv == 0 && !empty at.eventFileNm}">
					<c:set var="img0"
						value="${contextPath}/${at.eventFilePath}${at.eventFileNm}" />
				</c:when>
				<c:when test="${at.eventFileLv == 1 && !empty at.eventFileNm}">
					<c:set var="img1"
						value="${contextPath}/${at.eventFilePath}${at.eventFileNm}" />
				</c:when>
				<c:when test="${at.eventFileLv == 2 && !empty at.eventFileNm}">
					<c:set var="img2"
						value="${contextPath}/${at.eventFilePath}${at.eventFileNm}" />
				</c:when>
				<c:when test="${at.eventFileLv == 3 && !empty at.eventFileNm}">
					<c:set var="img3"
						value="${contextPath}/${at.eventFilePath}${at.eventFileNm}" />
				</c:when>
			</c:choose>
		</c:forEach>




		<div class="form-inline mb-2 title-area">
			<label class="input-group-addon mr-3 insert-label">업로드<br>이미지
			</label>

			<div class="mr-2 boardImg" id="contentImgArea0">
				<!-- img0 변수가 만들어진 경우 -->
				<c:if test="${!empty img0 }">
				
				<a href="${img0}" data-lightbox="example-set" style="display: block; width: auto;">
				<img id="contentImg0" src="${img0}">
				<div id = 'explain'>이미지 크게 보기</div>
				</a>
				
				</c:if>
			</div>


			<div class="mr-2 boardImg" id="contentImgArea1">
				<c:if test="${!empty img1 }">
				<a href="${img1}" data-lightbox="example-set">
					<img id="contentImg1" src="${img1 }">
									<div id = 'explain'>이미지 크게 보기</div>
				</a>
				</c:if>
			</div>

			<div class="mr-2 boardImg" id="contentImgArea2">
				<c:if test="${!empty img2 }">
				<a href="${img2}" data-lightbox="example-set">
					<img id="contentImg2" src="${img2 }">
									<div id = 'explain'>이미지 크게 보기</div>
				</a>
				</c:if>
			</div>

			<div class="mr-2 boardImg" id="contentImgArea3">
				<c:if test="${!empty img3 }">
				<a href="${img3}" data-lightbox="example-set">
					<img id="contentImg3" src="${img3 }">
									<div id = 'explain'>이미지 크게 보기</div>
				</a>
				</c:if>
			</div>
		</div>











		<!-- 관리자가 글 쓴 내용  -->

		<div id="content-box">
			<span id="member-content-detail">${board.eventBodContent}</span>
		</div>





		<c:if test="${loginMember.memberNo == board.memberNo }">
			<button type="button" class="btn btn-primary float-right mr-2" onclick="dlRequest('delete')">삭제</button>
			<button id="updateBtn" class="btn btn-primary float-right mr-2 "
				onclick="fnRequest('updateForm');";>수정</button>
		</c:if>
		<br> <br>
		<hr>


		<%-- 댓글 영역 --%>
		<jsp:include page="eventReply.jsp"></jsp:include>


	</div>












	<jsp:include page="../common/footer.jsp"></jsp:include>





	<form action="#" method="POST" name="requestForm">
		<input type="hidden" name="boardNo" value="${board.eventBodNo}">
		<input type="hidden" name="cp" value="${param.cp}">
	</form>


	<script>
		function fnRequest(addr) {
			// 현재 문서 내부에 name 속성 값이 requestForm인 요소의 action 속성 값을 변경
			document.requestForm.action = "../eventBoard2/" + addr;

			// 현재 문서 내부에 name 속성 값이 requestForm인 요소를 제출
			document.requestForm.submit();
		}
		
		
		function dlRequest(addr2){
		
			var boardNo = ${board.eventBodNo};
			
			swal({
				  title: "게시글을 삭제하시겠습니까?",
				  text: "삭제를 누르시면 게시글이 삭제됩니다.",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then((willDelete) => {
				  if (willDelete) {
					document.requestForm.action= "../eventBoard2/" + addr2+"?no="+boardNo;
					document.requestForm.submit();
				  } else {
				    swal("게시글 삭제가 취소되었습니다.");
				  }
				});
			
				
			
			
			
			
		}
		

		function deleteReply(replyNo) {

			if (confirm("정말로 삭제하시겠습니까?")) {
				var url = "${contextPath}/EventReply/deleteReply";

				$.ajax({
					url : url,
					data : {
						"replyNo" : replyNo
					},
					success : function(result) {
						if (result > 0) {
							selectReplyList(boardNo);

							swal({
								"icon" : "success",
								"title" : "댓글 삭제 성공"
							});
						}

					},
					error : function() {
						console.log("ajax 통신 실패");
					}
				});
			}

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