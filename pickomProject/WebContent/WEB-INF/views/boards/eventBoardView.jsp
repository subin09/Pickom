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


<link rel="stylesheet" type="text/css" href="css/01_header.css">
<link rel="stylesheet" type="text/css" href="css/02_footer.css">





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
div {
	border: 1px solid red;
}

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

.boardImg {
	width: 100px;
	height: 100px;
}

#contentImg0, #contentImg1, #contentImg2, #contentImg3 {
	width: 100px;
	height: 100px;
}

body {
	padding-top: 210px;
}
</style>


</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>




	<div class="container">


		<br> <br> <br> <br>

		<h1>행사게시판</h1>
		<div class=text-right>
			<a href="view?no=${board.eventBodNo-1}&cp=1" class="btn btn-primary">이전글</a>
			<a href="view?no=${board.eventBodNo+1}&cp=1" class="btn btn-primary">다음글</a>
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
		<span>시작일 : [${board.startDate}]</span> <br> <span>종료일 :
			[${board.finalDate}]</span> <br> <br>

		<!-- 회원이 글 쓴 내용 -->
		<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30"
			fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
                <path fill-rule="evenodd"
				d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
            </svg>

		<!-- 댓글 단 횟수 count -->
		<i class="bi bi-person-circle">닉네임 : ${board.memberNm }</i> <i
			class="bi bi-chat" id="comment-count"> <svg
				xmlns="http://www.w3.org/2000/svg" width="16" height="16"
				fill="currentColor" class="bi bi-chat" viewBox="0 0 16 16">
                    <path
					d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z" />
                </svg> <span>댓글 단 횟수</span>
		</i>







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






		<div class="form-inline mb-2">
			<label class="input-group-addon mr-3 insert-label">업로드<br>이미지
			</label>
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








		<br> <br>



		<!-- 관리자가 글 쓴 내용  -->

		<div id="content-box">
			<span id="member-content-detail">${board.eventBodContent}</span>
		</div>





		<c:if test="${loginMember.memberNo == board.memberNo }">
			<button type="button" class="btn btn-primary float-right mr-2" data-toggle="modal"
				data-target="#exampleModal">삭제</button>
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
	</script>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">정말 삭제하시겠습니까?</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">삭제를 누르면 게시글이 삭제됩니다.</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">취소</button>
						<a href="../eventBoard2/delete" class="btn btn-primary">삭제</a>
				</div>
			</div>
		</div>
	</div>

</body>

</html>