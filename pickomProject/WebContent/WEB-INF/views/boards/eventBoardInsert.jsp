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
/* div {
	border: 1px solid red;
} */



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


	<div class="container shadow p-3 mb-5 bg-white rounded">
	
		<h1 style="margin-top: 70px;">행사 게시판 작성</h1>
		<hr>
		<form action="${contextPath}/eventBoard2/insert" id="writeForm" method="post"
			enctype="multipart/form-data" role="form"
			onsubmit="return boardValidate();">


			<div id="setDate-area">
				<label for="startDate">[행사 시작 날짜] : &nbsp</label>
				<input type="date" id="startDate" name="startDate">
				<label for=""> &nbsp&nbsp  ~~~ &nbsp&nbsp  </label>
				<label for="finalDate">[행사 종료 날짜] : &nbsp</label>
				<input type="date" id="finalDate" name="finalDate">
			</div>
			<hr>


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
					name="boardTitle">
			</div>
			<hr>





			<div class="form-inline mb-2">
				<label class="input-group-addon mr-3 insert-label">업로드<br>이미지
				</label>
				<div class="mr-2 boardImg" id="contentImgArea0">
					<img id="contentImg0">
				</div>
				<div class="mr-2 boardImg" id="contentImgArea1">
					<img id="contentImg1">
				</div>

				<div class="mr-2 boardImg" id="contentImgArea2">
					<img id="contentImg2">
				</div>

				<div class="mr-2 boardImg" id="contentImgArea3">
					<img id="contentImg3">
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
					id="boardContent" rows="20" name="boardContent" style="resize: none;"></textarea>

			</div>
			<div id="submit-btn-area" style="width:100%; height: 40px; ">
			<input class="btn btn-primary float-right mr-2" type="submit" value="등록">
			</div>
		</form>

	</div>



	<jsp:include page="../common/footer.jsp"></jsp:include>




	<script>
		(function printToday() {
			// 오늘 날짜 출력 
			var today = new Date();
			var month = (today.getMonth() + 1);
			var date = today.getDate();

			var str = today.getFullYear() + "-"
					+ (month < 10 ? "0" + month : month) + "-"
					+ (date < 10 ? "0" + date : date);
			$("#today").html(str);
		})();

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

			if ($("#startDate").val() == "") {
				alert("시작날짜를 선택해 주세요.");
				$("#startDate").focus();
				return false;
			}
			if ($("#finalDate").val() == "") {
				alert("마지막 날짜를 선택해 주세요.");
				$("#fianlDate").focus();
				return false;
			}
			
			
			
		}

		$(function(){
			$("#writeForm").submit(function(){
				var startDate = $("#startDate").val();
				var finalDate = $("#finalDate").val();
				
				var startArray = startDate.split('-');
				var finalArray = finalDate.split('-');
				
				var start_date = new Date(startArray[0], startArray[1], startArray[2]);
				var final_date = new Date(finalArray[0], finalArray[1], finalArray[2]);
				
				if(start_date.getTime() > final_date.getTime()){
					alert("시작날짜는 종료날짜보다 작아야 합니다.");
					return false;
				}
				
			});
		});
		
		
		
		
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
		
		
		
		
		
	</script>



</body>
</html>