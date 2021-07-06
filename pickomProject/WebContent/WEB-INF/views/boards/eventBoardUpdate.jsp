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


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- Bootstrap core JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>







<style>
div {
	border: 1px solid red;
}

body {
	padding-top: 280px;
}

.boardImg {
	cursor: pointer;
	width: 200px;
	height: 200px;
	border: 1px solid #ced4da;
	position: relative;
}

.boardImg>img {
	max-width: 100%;
	max-height: 100%;
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin: auto;
}

div.img {
	text-align: center;
}

input#search {
	width: 500px;
}

a {
	font-weight: bold !important;
	color: black !important;
	text-align: center !important;
	width: 10em !important;
}

a:hover {
	color: orange !important;
}

#inputGroup-sizing-default {
	font-weight: bold;
}

#content-title {
	background-color: grey;
	color: white;
}

#btn-cancel {
	margin-left: 45%;
}

#fileArea {
	display: none;
}
</style>




</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<div class="container">
		<h1>행사 게시글  수정</h1>
		<form action="${contextPath}/eventBoard2/update" method="post"
			enctype="multipart/form-data" role="form"
			onsubmit="return boardValidate();">

			<div id="setDate-area">
				<input type="date" name="startDate" value="${board.startDate}"> <label for="">~~~</label>
				<input type="date" name="finalDate" value="${board.finalDate}">
			</div>


			<c:if test="${ !empty category}">
				<div class="mb-2">
					<label class="input-group-addon mr-3 insert-label">카테고리 : </label>
					<select class="custom-select" id="categoryCode" name="categoryCode"
						style="width: 150px;">
						<c:forEach items="${category}" var="ec">
							<option value="${ec.eventCategoryCd}">${ec.eventCategoryNm}</option>
						</c:forEach>
					</select>
				</div>
			</c:if>


			<hr>


			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text" id="inputGroup-sizing-default">제목</span>
				</div>
				<input type="text" class="form-control"
					aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default" id="boardTitle"
					name="boardTitle" value="${board.eventBodTitle}">
			</div>
			<hr>





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

					<c:if test="${!empty img0}">
						<img id="contentImg0" src="${img0}">
					</c:if>
					<c:if test="${empty img0}">
						<img id="contentImg0">
					</c:if>

				</div>
				<div class="mr-2 boardImg" id="contentImgArea1">
					<c:if test="${!empty img1}">
						<img id="contentImg1" src="${img1}">
					</c:if>
					<c:if test="${empty img1}">
						<img id="contentImg1">
					</c:if>
				</div>

				<div class="mr-2 boardImg" id="contentImgArea2">
					<c:if test="${!empty img2}">
						<img id="contentImg2" src="${img2}">
					</c:if>
					<c:if test="${empty img2}">
						<img id="contentImg2">
					</c:if>
				</div>

				<div class="mr-2 boardImg" id="contentImgArea3">
					<c:if test="${!empty img3}">
						<img id="contentImg3" src="${img3}">
					</c:if>
					<c:if test="${empty img3}">
						<img id="contentImg3">
					</c:if>
				</div>
			</div>





			<!-- 파일 업로드 하는 부분 -->
			<div id="fileArea">
				<!--  multiple 속성
                  - input 요소 하나에 둘 이상의 값을 입력할 수 있음을 명시 (파일 여러개 선택 가능)
                -->
				<input type="file" id="img0" name="img0" onchange="LoadImg(this,0)">
				<input type="file" id="img1" name="img1" onchange="LoadImg(this,1)">
				<input type="file" id="img2" name="img2" onchange="LoadImg(this,2)">
				<input type="file" id="img3" name="img3" onchange="LoadImg(this,3)">
			</div>






			<div class="form-group" id="content-title">
				<label for="exampleFormControlTextarea1">내용</label>
				<textarea class="form-control exampleFormControlTextarea1"
					id="boardContent" rows="20" name="boardContent"
					style="resize: none;">${board.eventBodContent}</textarea>
			</div>


			<div class="text-center">
				<button type="submit" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-primary">목록으로</button>
			</div>

			
			<input type="hidden" name="cp" value="${param.cp}">
			<input type="hidden" name="boardNo" value="${board.eventBodNo}">





		</form>

	</div>



	<jsp:include page="../common/footer.jsp"></jsp:include>




	<script>

		// 유효성 검사 
		function boardValidate() {
			if ($("#boardTitle").val().trim().length == 0) {
				alert("제목을 입력해 주세요.");
				$("#title").focus();
				return false;
			}

			if ($("#boardContent").val().trim().length == 0) {
				alert("내용을 입력해 주세요.");
				$("#content").focus();
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

		// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
		function LoadImg(value, num) {
			if (value.files && value.files[0]) {
				var reader = new FileReader();
				// 자바스크립트 FileReader
				// 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는 File 혹은 Blob객체를 이용해 파일의 내용을 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 객체

				reader.readAsDataURL(value.files[0]);
				// FileReader.readAsDataURL()
				// 지정된의 내용을 읽기 시작합니다. Blob완료되면 result속성 data:에 파일 데이터를 나타내는 URL이 포함 됩니다.

				// FileReader.onload
				// load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료 되었을 때마다 발생합니다.
				reader.onload = function(e) {
					//console.log(e.target.result);
					// e.target.result
					// -> 파일 읽기 동작을 성공한 객체에(fileTag) 올라간 결과(이미지 또는 파일)

					$(".boardImg").eq(num).children("img").attr("src",
							e.target.result);
				}

			}
		}
		
		// 카테고리 이전 게시글에 입력된 값으로 세팅
		const categoryName = "${board.eventCategoryNm}";
		$("#categoryCode > option").each(function(index, item){
			
			if($(item).text() == categoryName){
				// option 태그에 써져있는 내용이 이전 게시글의 카테고리명과 같다면
				
				$(item).prop("selected",true);
			}
			
		});
			
		
		
	</script>



</body>
</html>